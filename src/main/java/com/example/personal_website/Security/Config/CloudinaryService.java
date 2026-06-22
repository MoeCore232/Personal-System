package com.example.personal_website.Security.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService (Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile (MultipartFile file) {
        try {
            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );
            String imgUrl = (String) result.get("secure_url");
            return imgUrl;

        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }

    }

}
