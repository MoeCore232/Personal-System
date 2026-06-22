package com.example.personal_website.Core_System.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatsRepo extends JpaRepository<Stats, UUID> {
}
