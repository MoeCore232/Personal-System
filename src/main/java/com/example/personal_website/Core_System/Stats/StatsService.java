package com.example.personal_website.Core_System.Stats;

import com.example.personal_website.Core_System.Post.Post;
import com.example.personal_website.Core_System.Post.PostRepo;
import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StatsService {

    private final StatsRepo statsRepo;
    private final PostRepo postRepo;
    private final UUID id = UUID.fromString("e6c49d6f-a0c1-4a4f-982b-5b2e5924e6a9");

    public StatsService (StatsRepo statsRepo, PostRepo postRepo) {
        this.statsRepo = statsRepo;
        this.postRepo = postRepo;
    }

    public void addNewVisit () {
        Optional<Stats> stats = statsRepo.findById(id);
        Stats visits = stats.get();
        try {
            visits.setVisitsCount(visits.getVisitsCount() + 1);
            statsRepo.save(visits);
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public List<Stats> getAllVisits () {
        List<Stats> stats = statsRepo.findAll();
        try {
            return stats;
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public void newPostView (UUID postId) {
        Post findPost = postRepo.findById(postId)
                .orElseThrow(
                        () -> CustomResponseException.unExpectedErrorOccurred());
        try {
            findPost.setViews(findPost.getViews() + 1);
            postRepo.save(findPost);
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public int totalPostViews () {
        List<Post> photos = postRepo.findAllByPostType(Post.PostType.PHOTO);
        try {
            int totalViews;
            for (Post photo : photos) {
                System.out.println("Views: " + photo.getViews());
            }
            return 0;
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public void createStats () {
        try {
            Stats stats = new Stats();
            stats.setVisitsCount(0);
            statsRepo.save(stats);
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

}
