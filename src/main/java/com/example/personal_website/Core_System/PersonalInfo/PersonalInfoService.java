package com.example.personal_website.Core_System.PersonalInfo;

import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonalInfoService {

    private final PersonalInfoRepo personalInfoRepo;

    public PersonalInfoService (PersonalInfoRepo personalInfoRepo) {
        this.personalInfoRepo = personalInfoRepo;
    }

    public List<PersonalInfo> getAllPersonalInfos () {
        try {
            List<PersonalInfo> personalInfos = personalInfoRepo.findAll();
            return personalInfos;
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

   public String updateInfo (UUID infoId, PersonalInfoDto.UpdateInfo updateInfo) {
       PersonalInfo findInfo = personalInfoRepo.findById(infoId)
               .orElseThrow(() -> CustomResponseException.idIsNotFound(infoId));
        try {
            PersonalInfo personalInfo = PersonalInfo.updateInfo(findInfo, updateInfo);
            personalInfoRepo.save(personalInfo);

            return "Info updated successfully!";
        } catch (Exception exception) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
   }

   public PersonalInfo createValue (PersonalInfoDto.CreateInfo createInfo) {
        PersonalInfo personalInfo = PersonalInfo.createInfo(createInfo);
        personalInfoRepo.save(personalInfo);
        return personalInfo;
   }

}
