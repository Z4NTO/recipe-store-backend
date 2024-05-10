package de.fmm.recipestore.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredient")
    Set<RecipeIngredient> recipes;
}
