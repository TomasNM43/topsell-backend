package com.topsell.backend.repository;
import com.topsell.backend.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    // Buscar solo los activos y ordenarlos
    List<Banner> findByActiveTrueOrderBySortOrderAsc();
}
