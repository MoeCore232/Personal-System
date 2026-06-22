package com.example.personal_website.Core_System.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {

    List<Post> findAllByPostType (Post.PostType postType);

}
