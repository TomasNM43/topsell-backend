package com.topsell.backend.controller;

import com.topsell.backend.entity.Banner;
import com.topsell.backend.repository.BannerRepository;
import com.topsell.backend.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    // ========== ENDPOINTS PÚBLICOS (TIENDA) ==========
    
    @GetMapping
    public List<Banner> getActiveBanners() {
        // Retorna solo los activos y en orden (1, 2, 3...)
        return bannerRepository.findByActiveTrueOrderBySortOrderAsc();
    }

    // ========== ENDPOINTS ADMIN ==========
    
    @GetMapping("/admin")
    public List<Banner> getAllBannersAdmin() {
        return bannerRepository.findAll();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        return bannerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/admin")
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerRepository.save(banner);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable Long id, @RequestBody Banner bannerDetails) {
        return bannerRepository.findById(id)
                .map(banner -> {
                    try {
                        // Si la URL de la imagen cambió, eliminar la anterior de Cloudinary
                        if (bannerDetails.getImageUrl() != null && 
                            !bannerDetails.getImageUrl().equals(banner.getImageUrl()) &&
                            banner.getImageUrl() != null && !banner.getImageUrl().isEmpty()) {
                            cloudinaryService.deleteImage(banner.getImageUrl());
                        }
                    } catch (Exception e) {
                        // Continuar aunque falle la eliminación de Cloudinary
                    }
                    
                    banner.setImageUrl(bannerDetails.getImageUrl());
                    banner.setTitle(bannerDetails.getTitle());
                    banner.setActive(bannerDetails.getActive());
                    banner.setSortOrder(bannerDetails.getSortOrder());
                    return ResponseEntity.ok(bannerRepository.save(banner));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        return bannerRepository.findById(id)
                .map(banner -> {
                    try {
                        // Eliminar imagen de Cloudinary
                        if (banner.getImageUrl() != null && !banner.getImageUrl().isEmpty()) {
                            cloudinaryService.deleteImage(banner.getImageUrl());
                        }
                        
                        // Eliminar el banner de la base de datos
                        bannerRepository.delete(banner);
                        return ResponseEntity.ok().build();
                    } catch (Exception e) {
                        return ResponseEntity.status(500)
                                .body("Error al eliminar banner: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}