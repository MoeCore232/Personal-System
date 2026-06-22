package com.example.personal_website.Core_System.Post;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Post {

    public enum PostType { VIDEO, PHOTO }

    public final static LocalDateTime lastUpdatedAt = LocalDateTime.now();

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "post_url", nullable = false, updatable = false)
    private String postUrl;

    @Column(name = "public_id", nullable = false, updatable = false)
    private String publicId;

    @Column(name = "post_type", nullable = false, updatable = false)
    private PostType postType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "views", nullable = false)
    private int views = 0;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Post (UUID id, String postUrl, String publicId, PostType postType, String description,
                 String location, int views, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.postUrl = postUrl;
        this.publicId = publicId;
        this.postType = postType;
        this.description = description;
        this.location = location;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Post () {}

    public UUID getId () {return id;}

    public String getPostUrl () {return postUrl;}
    public void setPostUrl (String postUrl) {this.postUrl = postUrl;}

    public String getPublicId () {return publicId;}
    public void setPublicId (String publicId) {this.publicId = publicId;}

    public PostType getPostType () {return postType;}
    public void setPostType (PostType postType) {this.postType = postType;}

    public String getDescription () {return description;}
    public void setDescription (String description) {this.description = description;}

    public String getLocation () {return location;}
    public void setLocation (String location) {this.location = location;}

    public int getViews () {return views;}
    public void setViews (int views) {this.views = views;}

    public LocalDateTime getCreatedAt () {return createdAt;}

    public LocalDateTime getUpdatedAt () {return updatedAt;}
    public void setUpdatedAt (LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

}
