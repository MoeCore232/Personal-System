package com.example.personal_website.Core_System.CV;

import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CvService {

    private final CvRepo cvRepo;

    public CvService (CvRepo cvRepo) {
        this.cvRepo = cvRepo;
    }

    public List<Cv> getAllSections () {
        try {
            List<Cv> sections = cvRepo.findAll();
            return sections;
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String createSection (CvDto.CreateSection createSection) {
        try {
            Cv section = Cv.createSection(createSection);
            cvRepo.save(section);
            return "Section created Successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String updateSection (UUID sectionId, CvDto.UpdateSection updateSection) {
        Cv findSection = cvRepo.findById(sectionId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(sectionId));
        try {
            Cv newSection = Cv.updateSection(findSection, updateSection);
            cvRepo.save(newSection);
            return "Section updated successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String deleteSection (UUID sectionId) {
        cvRepo.findById(sectionId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(sectionId));
        try {
            cvRepo.deleteById(sectionId);
            return "Section deleted successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

}
