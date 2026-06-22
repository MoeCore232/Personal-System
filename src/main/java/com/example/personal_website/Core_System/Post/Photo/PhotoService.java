package com.example.personal_website.Core_System.Post.Photo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.personal_website.Core_System.Post.FileValidator;
import com.example.personal_website.Core_System.Post.Post;
import com.example.personal_website.Core_System.Post.PostRepo;
import com.example.personal_website.Core_System.Post.PostResponseDto;
import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final FileValidator fileValidator;
    private final PostRepo postRepo;
    private final Cloudinary cloudinary;

    public PhotoService (FileValidator fileValidator, PostRepo postRepo, Cloudinary cloudinary) {
        this.fileValidator = fileValidator;
        this.postRepo = postRepo;
        this.cloudinary = cloudinary;
    }

    public List<PostResponseDto> getAllPhotos () {
        try {
            List<Post> photos = postRepo.findAllByPostType(Post.PostType.PHOTO);
            return photos.stream().map(m -> new PostResponseDto(
                    m.getId(),
                    m.getPostType(),
                    m.getPostUrl(),
                    m.getDescription(),
                    m.getViews(),
                    m.getCreatedAt()
                            )).collect(Collectors.toList());
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public Post getPhotoById (UUID photoId) {
        Post photo = postRepo.findById(photoId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(photoId));
        try {
            return photo;
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String deletePhoto (UUID photoId) {
        Optional<Post> findPhoto = postRepo.findById(photoId);
        if (findPhoto.isEmpty()) {
            throw CustomResponseException.idIsNotFound(photoId);
        }
        try {
            Post photo = findPhoto.get();
            Map result = cloudinary.uploader().destroy(
                    photo.getPublicId(), ObjectUtils.emptyMap()
            );
            String status = (String) result.get("result");
            if (!"ok".equals(status)) {
                throw new RuntimeException("Error: Cloudinary deleted failed");
            }
            postRepo.deleteById(photoId);
            return "Photo deleted successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    @Transactional
    public String createPhoto (PhotoDto.CreatePhoto createPhoto, MultipartFile file) {
        try {
            fileValidator.imageValidator(file);

            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );
            String url = (String) result.get("secure_url");
            String publicId = (String) result.get("public_id");

            Photo photo = Photo.createPhoto(createPhoto, url, publicId);
            postRepo.save(photo);
            return "Photo created successfully!";
        } catch (IOException e) {
            System.out.println("Error: " + e);
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String updatePhoto (PhotoDto.UpdatePhoto updatePhoto, UUID photoId) {
        Post findPhoto = postRepo.findById(photoId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(photoId));
        try {
            Post photo = Photo.updatePhoto(updatePhoto, findPhoto);
            postRepo.save(photo);
            return "Photo updated successfully";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }


}
