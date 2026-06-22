package com.example.personal_website.Core_System.PersonalInfo;

import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;

    public PersonalInfoController (PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/get-all-personal-infos")
    public ResponseEntity<GlobalResponse<List<PersonalInfo>>> getAllPersonalInfos () {
        List<PersonalInfo> personalInfos = personalInfoService.getAllPersonalInfos();
        return new ResponseEntity<>(new GlobalResponse<>(personalInfos), HttpStatus.OK);
    }

    @PutMapping("/update-personal-info/{infoId}")
    public ResponseEntity<GlobalResponse<String>> updatePersonalInfo (
            @PathVariable UUID infoId, @RequestBody PersonalInfoDto.UpdateInfo updateInfo)
    {
        String updatedInfo = personalInfoService.updateInfo(infoId, updateInfo);
        return new ResponseEntity<>(new GlobalResponse<>(updatedInfo), HttpStatus.OK);
    }

    @PostMapping("/create-personal-info")
    public ResponseEntity<GlobalResponse<PersonalInfo>> createInfo (
            @RequestBody PersonalInfoDto.CreateInfo createInfo)
    {
        PersonalInfo personalInfo = personalInfoService.createValue(createInfo);
        return new ResponseEntity<>(new GlobalResponse<>(personalInfo), HttpStatus.CREATED);
    }

}
