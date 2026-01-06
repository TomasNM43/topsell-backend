package com.topsell.backend.repository;
import com.topsell.backend.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Si necesitas buscar por slug
}
