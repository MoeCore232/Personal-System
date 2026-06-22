package com.example.personal_website.Core_System.CV;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CvDto {

    public record CreateSection (
            @NotBlank(message = "Error: Title is required!")
            @Size(min = 1, message = "Error: Min character for title  is 1")
            String title,

            @NotBlank(message = "Error: Content is required!")
            @Size(min = 5, message = "Error: Min character for content  is 5")
            String content
    ) {}

    public record UpdateSection (
            String newTitle,
            String newContent
    ) {}

}
