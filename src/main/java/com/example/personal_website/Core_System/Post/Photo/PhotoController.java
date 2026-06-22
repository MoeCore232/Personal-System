package com.example.personal_website.Core_System.Post.Photo;

import com.example.personal_website.Core_System.Post.Post;
import com.example.personal_website.Core_System.Post.PostResponseDto;
import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController (PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/get-all-photos")
    public ResponseEntity<GlobalResponse<List<PostResponseDto>>> getAllVideos () {
        List<PostResponseDto> photos = photoService.getAllPhotos();
        return new ResponseEntity<>(new GlobalResponse<>(photos), HttpStatus.OK);
    }

    @GetMapping("/get-photo-by-id/{photoId}")
    public ResponseEntity<GlobalResponse<Post>> getPhotoById (@PathVariable UUID photoId) {
        Post photo = photoService.getPhotoById(photoId);
        return new ResponseEntity<>(new GlobalResponse<>(photo), HttpStatus.OK);
    }

    @DeleteMapping("/delete-photo/{photoId}")
    public ResponseEntity<GlobalResponse<String>> deletePhoto (@PathVariable UUID photoId) {
        String photo = photoService.deletePhoto(photoId);
        return new ResponseEntity<>(new GlobalResponse<>(photo), HttpStatus.OK);
    }

    @PostMapping("/create-photo")
    public ResponseEntity<GlobalResponse<String>> createPhoto
            (@ModelAttribute @Valid PhotoDto.CreatePhoto createPhoto, @RequestParam MultipartFile file) {
        String photo = photoService.createPhoto(createPhoto, file);
        return new ResponseEntity<>(new GlobalResponse<>(photo), HttpStatus.OK);
    }

    @PutMapping("/update-photo/{photoId}")
    public ResponseEntity<GlobalResponse<String>> updatePhoto (
            @RequestBody PhotoDto.UpdatePhoto updatePhoto, @PathVariable UUID photoId)
    {
        String photo = photoService.updatePhoto(updatePhoto, photoId);
        return new ResponseEntity<>(new GlobalResponse<>(photo), HttpStatus.OK);
    }

}
