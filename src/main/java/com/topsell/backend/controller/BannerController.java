package com.topsell.backend.controller;

import com.topsell.backend.entity.Banner;
import com.topsell.backend.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerRepository bannerRepository;

    @GetMapping
    public List<Banner> getActiveBanners() {
        // Retorna solo los activos y en orden (1, 2, 3...)
        return bannerRepository.findByActiveTrueOrderBySortOrderAsc();
    }
}