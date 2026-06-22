package com.example.personal_website.Core_System.PersonalInfo;


import java.util.UUID;

public class PersonalInfoDto {

    public record UpdateInfo (
            String newValue,
            UUID infoId
    ) {}

    public record CreateInfo (
            PersonalInfo.InfoType key,
            String value
    ) {}

}
