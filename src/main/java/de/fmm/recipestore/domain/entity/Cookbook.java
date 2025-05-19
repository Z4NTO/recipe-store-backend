package de.fmm.recipestore.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String name;

}
