package com.example.personal_website.Core_System.Post.Video;

public class VideoDto {

    public record CreateVideo (
            String description,
            String location
    ) {}

    public record UpdateVideo (
            String newDescription,
            String location
    ) {}

}
