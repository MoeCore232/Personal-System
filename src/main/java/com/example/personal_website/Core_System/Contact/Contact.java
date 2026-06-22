package com.example.personal_website.Core_System.Contact;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contacts")
public class Contact {

    private static LocalDateTime lastUpdateContact = LocalDateTime.now();

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "icon", nullable = false)
    private String icon;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private Contact (UUID id, String title, String link, String icon, LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.icon = icon;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    private Contact () {}

    public static Contact createContact (ContactDto.CreateContact createContact) {
        Contact contact = new Contact();
        contact.title = createContact.title();
        contact.link = createContact.link();
        contact.icon = createContact.icon();
        contact.updatedAt = lastUpdateContact;
        return contact;
    }

    public static Contact updateContact (Contact contact, ContactDto.UpdateContact updateContact) {
        contact.title = updateContact.newTitle();
        contact.link = updateContact.newLink();
        contact.icon = updateContact.newIcon();
        contact.updatedAt = lastUpdateContact;
        return contact;
    }

    public UUID getId () {return id;}
    public String getTitle () {return title;}
    public String getLink () {return link;}
    public String getIcon () {return icon;}
    public LocalDateTime getCreatedAt () {return createdAt;}
    public LocalDateTime getUpdatedAt () {return updatedAt;}

}
