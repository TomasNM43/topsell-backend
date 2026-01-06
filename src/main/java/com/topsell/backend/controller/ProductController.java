package com.topsell.backend.controller;

import com.topsell.backend.entity.Product;
import com.topsell.backend.repository.CategoryRepository;
import com.topsell.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Detalle de producto por URL (slug)
    @GetMapping("/{slug}")
    public Product getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Productos por Categoría (para los carruseles específicos de la Home)
    // Ejemplo: /api/products/category/ropa
    @GetMapping("/category/{categorySlug}")
    public List<Product> getProductsByCategory(@PathVariable String categorySlug) {
        Long categoryId = categoryRepository.findBySlug(categorySlug)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"))
                .getId();

        return productRepository.findByCategoryId(categoryId);
    }
}