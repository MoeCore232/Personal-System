package com.example.personal_website.Core_System.PersonalInfo;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "personal_infos")
public class PersonalInfo {

    public enum InfoType {Profile_Photo, Upper_Home_Title, Lower_Home_Title, Home_Paragraph, About}

    private static LocalDateTime lastUpdateInfo = LocalDateTime.now();

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "info_type", nullable = false, unique = true, updatable = false)
    private InfoType key;

    @Column(name = "info", nullable = false)
    private String value;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public PersonalInfo (UUID id, InfoType key, String value, LocalDateTime updatedAt) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.updatedAt = updatedAt;
    }
    public PersonalInfo () {}

    public static PersonalInfo updateInfo (PersonalInfo personalInfo, PersonalInfoDto.UpdateInfo updateInfo) {
        personalInfo.value = updateInfo.newValue();
        personalInfo.updatedAt = lastUpdateInfo;
        return personalInfo;
    }

    public static PersonalInfo createInfo (PersonalInfoDto.CreateInfo createInfo) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.key = createInfo.key();
        personalInfo.value = createInfo.value();
        personalInfo.updatedAt = lastUpdateInfo;
        return personalInfo;
    }

    public UUID getId () {return id;}
    public InfoType getKey () {return key;}
    public String getValue () {return value;}
    public LocalDateTime getUpdatedAt () {return updatedAt;}

}
