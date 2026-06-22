package com.example.personal_website.Core_System.CV;

import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    public CvController (CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/get-all-sections")
    public ResponseEntity<GlobalResponse<List<Cv>>> getAllSection () {
        List<Cv> sections = cvService.getAllSections();
        return new ResponseEntity<>(new GlobalResponse<>(sections), HttpStatus.OK);
    }

    @PostMapping("/create-section")
    public ResponseEntity<GlobalResponse<String>> createSection (@RequestBody CvDto.CreateSection createSection) {
        String section = cvService.createSection(createSection);
        return new ResponseEntity<>(new GlobalResponse<>(section), HttpStatus.CREATED);
    }

    @PutMapping("/update-section/{sectionId}")
    public ResponseEntity<GlobalResponse<String>> updateSection (
            @PathVariable UUID sectionId, @RequestBody CvDto.UpdateSection updateSection)
    {
        String newSection = cvService.updateSection(sectionId, updateSection);
        return new ResponseEntity<>(new GlobalResponse<>(newSection), HttpStatus.OK);
    }

    @DeleteMapping("/delete-section/{sectionId}")
    public ResponseEntity<GlobalResponse<String>> deleteSection (@PathVariable UUID sectionId) {
        String deleted = cvService.deleteSection(sectionId);
        return new ResponseEntity<>(new GlobalResponse<>(deleted), HttpStatus.OK);
    }

}
