package com.example.personal_website.Core_System.Stats;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "stats")
public class Stats {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "visits_count", nullable = false)
    private int visitsCount;

    public Stats (UUID id, int visitsCount) {
        this.id = id;
        this.visitsCount = visitsCount;
    }
    public Stats () {}

    public UUID getId () {return id;}
    public int getVisitsCount () {return visitsCount;}
    public void setVisitsCount (int visitsCount) {this.visitsCount = visitsCount;}

}
