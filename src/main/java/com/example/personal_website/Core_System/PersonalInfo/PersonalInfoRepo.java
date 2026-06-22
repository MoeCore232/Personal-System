package com.example.personal_website.Core_System.PersonalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonalInfoRepo extends JpaRepository<PersonalInfo, UUID> {

}
