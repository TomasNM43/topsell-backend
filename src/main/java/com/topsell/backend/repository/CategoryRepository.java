package com.topsell.backend.repository;

import com.topsell.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Select * from categories where slug = ?
    Optional<Category> findBySlug(String slug);

    // Para validar que no repitas nombres al crear
    boolean existsByName(String name);
}
