package com.example.personal_website.Core_System.CV;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cvs")
public class Cv {

    private static LocalDateTime lastUpdateSection = LocalDateTime.now();

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private Cv (UUID id, String title, String content, LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    private Cv () {}

    public static Cv createSection (CvDto.CreateSection createSection) {
        Cv section = new Cv();
        section.title = createSection.title();
        section.content = createSection.content();
        section.updatedAt = lastUpdateSection;
        return section;
    }

    public static Cv updateSection (Cv section, CvDto.UpdateSection updateSection) {
        section.title = updateSection.newTitle();
        section.content = updateSection.newContent();
        section.updatedAt = lastUpdateSection;
        return section;
    }

    public UUID getId () {return id;}
    public String getTitle () {return title;}
    public String getContent () {return content;}
    public LocalDateTime getCreatedAt () {return createdAt;}
    public LocalDateTime getUpdatedAt () {return updatedAt;}

}
