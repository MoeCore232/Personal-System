package com.example.personal_website.Core_System.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostResponseDto {

    private UUID id;
    private Post.PostType postType;
    private String postUrl;
    private String description;
    private int views;
    private LocalDateTime createdAt;

    public PostResponseDto(UUID id, Post.PostType postType, String postUrl, String description,
                           int views, LocalDateTime createdAt) {
        this.id = id;
        this.postType = postType;
        this.postUrl = postUrl;
        this.description = description;
        this.views = views;
        this.createdAt = createdAt;
    }

    public UUID getId () {return id;}
    public Post.PostType getPostType () {return postType;}
    public String getPostUrl () {return postUrl;}
    public String getDescription () {return description;}
    public int getViews () {return views;}
    public LocalDateTime getCreatedAt () {return createdAt;}

}
