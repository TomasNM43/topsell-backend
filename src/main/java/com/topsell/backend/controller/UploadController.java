package com.topsell.backend.controller;

import com.topsell.backend.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam(value = "folder", defaultValue = "general") String folder) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file, folder);
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al subir imagen: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/image")
    public ResponseEntity<?> deleteImage(@RequestBody Map<String, String> payload) {
        try {
            String imageUrl = payload.get("imageUrl");
            cloudinaryService.deleteImage(imageUrl);
            return ResponseEntity.ok(Map.of("message", "Imagen eliminada correctamente"));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al eliminar imagen: " + e.getMessage()));
        }
    }
}
