package de.fmm.recipestore.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String title;

    @ManyToOne
    private Cookbook cookbook;

    @ManyToMany
    private Set<Tag> tags;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;

    private String notes;

}
