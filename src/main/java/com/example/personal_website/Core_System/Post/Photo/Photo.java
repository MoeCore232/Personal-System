package com.example.personal_website.Core_System.Post.Photo;

import com.example.personal_website.Core_System.Post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo extends Post {

    private Photo () {}

    public static Photo createPhoto (PhotoDto.CreatePhoto createPhoto, String url, String publicId) {
        Photo photo = new Photo();
        photo.setPublicId(publicId);
        photo.setPostUrl(url);
        photo.setPostType(PostType.PHOTO);
        photo.setDescription(createPhoto.description());
        photo.setLocation(createPhoto.location());
        photo.setUpdatedAt(lastUpdatedAt);
        return photo;
    }

    public static Post updatePhoto (PhotoDto.UpdatePhoto updatePhoto, Post photo) {
        photo.setDescription(updatePhoto.newDescription());
        photo.setLocation(updatePhoto.location());
        photo.setUpdatedAt(lastUpdatedAt);
        return photo;
    }

}
