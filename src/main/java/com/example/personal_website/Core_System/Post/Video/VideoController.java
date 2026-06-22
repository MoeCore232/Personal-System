package com.example.personal_website.Core_System.Post.Video;

import com.example.personal_website.Core_System.Post.Post;
import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    public VideoController (VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/get-all-videos")
    public ResponseEntity<GlobalResponse<List<Post>>> getAllVideos () {
        List<Post> videos = videoService.getAllVideos();
        return new ResponseEntity<>(new GlobalResponse<>(videos), HttpStatus.OK);
    }

    @GetMapping("/get-video-by-id/{videoId}")
    public ResponseEntity<GlobalResponse<Post>> getVideoById (@PathVariable UUID videoId) {
        Post video = videoService.getVideoById(videoId);
        return new ResponseEntity<>(new GlobalResponse<>(video), HttpStatus.OK);
    }

    @DeleteMapping("/delete-video/{videoId}")
    public ResponseEntity<GlobalResponse<String>> deleteVideo (@PathVariable UUID videoId) {
        String video = videoService.deleteVideo(videoId);
        return new ResponseEntity<>(new GlobalResponse<>(video), HttpStatus.OK);
    }

    @PostMapping("/create-video")
    public ResponseEntity<GlobalResponse<String>> createVideo (@ModelAttribute VideoDto.CreateVideo createVideo, @RequestParam MultipartFile file) {
        String video = videoService.createVideo(createVideo, file);
        return new ResponseEntity<>(new GlobalResponse<>(video), HttpStatus.CREATED);
    }

    @PutMapping("/update-video/{videoId}")
    public ResponseEntity<GlobalResponse<String>> updateVideo (
            @RequestBody VideoDto.UpdateVideo updateVideo, @PathVariable UUID videoId)
    {
        String video = videoService.updateVideo(updateVideo, videoId);
        return new ResponseEntity<>(new GlobalResponse<>(video), HttpStatus.OK);
    }

}
