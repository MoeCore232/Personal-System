package com.example.personal_website.Core_System.CV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CvRepo extends JpaRepository<Cv, UUID> {

}
