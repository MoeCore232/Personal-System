package com.example.personal_website.Core_System.Contact;

public class ContactDto {

    public record CreateContact (
            String title,
            String link,
            String icon
    ) {}

    public record UpdateContact (
            String newTitle,
            String newLink,
            String newIcon
    ) {}

}
