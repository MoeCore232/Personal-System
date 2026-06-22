package com.example.personal_website.Shared.ErrorHandling;

import java.util.UUID;

public class CustomResponseException extends RuntimeException {

    private String message;
    private int code;

    public CustomResponseException (String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static CustomResponseException idIsNotFound(UUID id) {
        return new CustomResponseException("Error: ID (" + id + ") is not found!", 404);
    }

    public static CustomResponseException unExpectedErrorOccurred () {
        return new CustomResponseException("Error: An unexpected error occurred. Please try again later", 500);
    }

    //==================
    // File Handling
    //==================
    public static CustomResponseException fileIsEmpty () {
        return new CustomResponseException("Error: File is empty!", 400);
    }
    public static CustomResponseException unSupportFileType (String stuff) {
        return new CustomResponseException("Error: UnSupport " + stuff +" type!", 400);
    }
    public static CustomResponseException fileTooLarge (String stuff, String maxSize) {
        return new CustomResponseException(
                "Error: "+ stuff + " too large. max " + maxSize +"allowed", 400
        );
    }
    public static CustomResponseException invalidFile () {
        return new CustomResponseException("Error: Invalid file!", 400);
    }
    public static CustomResponseException fileDimensionTooLarge (String stuff) {
        return new CustomResponseException(
                "Error: The "+stuff+" dimension are large. Maximum size is 2000x2000 px ", 400
        );
    }
    public static CustomResponseException fileDimensionTooSmall (String stuff) {
        return new CustomResponseException(
                "Error: " +stuff+ "dimension is too small. Minimum is 200x200 px", 400
        );
    }

    public String getMessage () {return message;}
    public int getCode () {return code;}

}
