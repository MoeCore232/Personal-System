package com.example.personal_website.Core_System.Post.Video;

import com.example.personal_website.Core_System.Post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video extends Post  {

    private Video () {}

    public static Video createVideo (VideoDto.CreateVideo createVideo, String url, String publicId) {
        Video video = new Video();
        video.setPostType((Post.PostType.VIDEO));
        video.setDescription(createVideo.description());
        video.setLocation(createVideo.location());
        video.setPostUrl(url);
        video.setPublicId(publicId);
        video.setUpdatedAt(lastUpdatedAt);
        return video;
    }

    public static Post updateVideo (VideoDto.UpdateVideo updateVideo, Post video) {
        video.setDescription(updateVideo.newDescription());
        video.setLocation(updateVideo.location());
        video.setUpdatedAt(lastUpdatedAt);
        return video;
    }

}
