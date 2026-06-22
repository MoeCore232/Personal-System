package com.example.personal_website.Core_System.Post.Video;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.personal_website.Core_System.Post.Post;
import com.example.personal_website.Core_System.Post.PostRepo;
import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService {

    private final PostRepo postRepo;
    private final Cloudinary cloudinary;

    public VideoService (PostRepo postRepo, Cloudinary cloudinary) {
        this.postRepo = postRepo;
        this.cloudinary = cloudinary;
    }

    public List<Post> getAllVideos () {
        try {
            List<Post> videos = postRepo.findAllByPostType(Post.PostType.VIDEO);
            return videos;
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public Post getVideoById (UUID videoId) {
        Post video = postRepo.findById(videoId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(videoId));
        try {
            return video;
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String deleteVideo (UUID videoId) {
        Optional<Post> video = postRepo.findById(videoId);
        if (video.isEmpty()) {
            throw CustomResponseException.idIsNotFound(videoId);
        }
        try {
            postRepo.deleteById(videoId);
            return "Video deleted successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String createVideo (VideoDto.CreateVideo createVideo, MultipartFile file) {
        try {
            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "video")
            );
            String url = (String) result.get("secure_url");
            String publicId = (String) result.get("public_id");

            Video video = Video.createVideo(createVideo, url, publicId);
            postRepo.save(video);
            return "Video created successfully!";
        } catch (IOException e) {
            System.out.println("Error: " + e);
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String updateVideo (VideoDto.UpdateVideo updateVideo, UUID videoId) {
        Post findVideo = postRepo.findById(videoId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(videoId));
        try {
            Post video = Video.updateVideo(updateVideo, findVideo);
            postRepo.save(video);
            return "Video updated successfully";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

}
