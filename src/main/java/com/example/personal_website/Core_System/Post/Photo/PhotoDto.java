package com.example.personal_website.Core_System.Post.Photo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PhotoDto {

    public record CreatePhoto (
            @NotBlank(message = "Error: Description is required!")
            @Size(max = 140, min = 1, message = "Error: Max character for description is 140, and min is 1")
            String description,

            String location
    ) {}

    public record UpdatePhoto (
            @NotBlank(message = "Error: Description is required!")
            @Size(max = 140, min = 1, message = "Error: Max character for description is 140, and min is 1")
            String newDescription,

            String location
    ) {}

}
