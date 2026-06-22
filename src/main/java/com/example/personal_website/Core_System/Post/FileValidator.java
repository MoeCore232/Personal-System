package com.example.personal_website.Core_System.Post;

import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileValidator {

    //======================
    // Allowed file sources
    //======================
    private static final List<String> allowedImageTypes = new ArrayList<>(
            List.of("image/png", "image/jpg", "image/jpeg")
    );
    private static final List<String> getAllowedVideosTypes = new ArrayList<>(
            List.of("video/mp4", "video/mov", "video/avi")
    );

    //===============
    // File configs
    //===============
    private static final long MAX_IMAGE_SIZE = 3 * 1024 * 1024;
    private static final long MAX_VIDEO_SIZE = 10 * 1024 * 1024;
    private static final int MAX_FILE_HEIGHT = 4096;
    private static final int MAX_FILE_WIDTH = 4096;
    private static final int MIN_FILE_HEIGHT = 200;
    private static final int MIN_FILE_WIDTH = 200;


    public void imageValidator (MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw CustomResponseException.fileIsEmpty();
        }
        String containType = file.getContentType();
        if (!allowedImageTypes.contains(containType)) {
            throw CustomResponseException.unSupportFileType("image");
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            throw CustomResponseException.fileTooLarge("Image", "5MB");
        }
        try (InputStream inputStream = file.getInputStream()) {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
            Iterator<ImageReader> reader = ImageIO.getImageReaders(imageInputStream);

            if (!reader.hasNext()) {
                throw CustomResponseException.invalidFile();
            }
            BufferedImage image = ImageIO.read(inputStream);
            if (image.getWidth() > MAX_FILE_WIDTH || image.getHeight() > MAX_FILE_HEIGHT) {
                throw CustomResponseException.fileDimensionTooLarge("image");
            }
            if (image.getWidth() < MIN_FILE_WIDTH || image.getHeight() < MIN_FILE_HEIGHT) {
                throw CustomResponseException.fileDimensionTooSmall("Image");
            }
        } catch (IOException e) {
            throw new CustomResponseException("Error: Filed reading the file!", 400);
        }
    }

    public void videoValidator (MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw CustomResponseException.fileIsEmpty();
        }
        String containType = file.getContentType();
        if (!getAllowedVideosTypes.contains(containType)) {
            throw CustomResponseException.unSupportFileType("video");
        }

        if (file.getSize() > MAX_VIDEO_SIZE) {
            throw CustomResponseException.fileTooLarge("video", "10MB");
        }

        try (InputStream inputStream = file.getInputStream()) {
            byte[] header = new byte[44];
            inputStream.read(header);

        } catch (IOException e) {
            throw CustomResponseException.invalidFile();
        }
    }

}
