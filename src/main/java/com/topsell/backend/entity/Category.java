package com.topsell.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    @Column(length = 500)
    private String description;

    // Agregado: Guardaremos la URL de la imagen, no los bytes.
    // Ejemplo: "https://mis3.aws.com/topsell/zapatillas.jpg" o "/uploads/cat1.jpg"
    @Column(length = 500)
    private String image;
}
