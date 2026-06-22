package com.example.personal_website.Core_System.Stats;

import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;
    private final int newVisit = 1;

    public StatsController (StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/new-visit")
    public String addNewVisit () {
        statsService.addNewVisit();
        return "Stats created successfully";
    }

    @GetMapping("/get-all-visits")
    public ResponseEntity<GlobalResponse<List<Stats>>> getAllVisits () {
        List<Stats> views = statsService.getAllVisits();
        return new ResponseEntity<>(new GlobalResponse<>(views), HttpStatus.OK);
    }

    @PostMapping("/new-post-view/{postId}")
    public void newPostView (@PathVariable UUID postId) {
        statsService.newPostView(postId);
    }

    @PostMapping("/create-stats")
    public ResponseEntity<GlobalResponse<String>> createStats () {
        statsService.createStats();
        return new ResponseEntity<>(new GlobalResponse<>("stats created successfully"), HttpStatus.OK);
    }


}
