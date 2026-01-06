package com.topsell.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "banners")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // URL de la imagen (ej: https://.../banner1.jpg)
    @Column(nullable = false)
    private String imageUrl;


    private String targetUrl;

    private Integer sortOrder;

    private Boolean active = true;
}