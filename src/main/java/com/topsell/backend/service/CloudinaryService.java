package com.topsell.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    
    @Autowired
    private Cloudinary cloudinary;
    
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("folder", "topsell/" + folder);
        uploadParams.put("resource_type", "image");
        
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
        return uploadResult.get("url").toString();
    }
    
    public void deleteImage(String imageUrl) throws IOException {
        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    
    private String extractPublicId(String imageUrl) {
        // Extrae el public_id de la URL de Cloudinary
        // Ejemplo: https://res.cloudinary.com/demo/image/upload/v1234/topsell/products/image.jpg
        // Public ID: topsell/products/image
        String[] parts = imageUrl.split("/upload/");
        if (parts.length > 1) {
            String pathWithVersion = parts[1];
            String path = pathWithVersion.replaceFirst("v\\d+/", "");
            return path.substring(0, path.lastIndexOf('.'));
        }
        return "";
    }
}