package de.fmm.recipestore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String title;

    @ManyToOne
    private Cookbook cookbook;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Set<RecipeIngredient> recipeIngredients;

    private String notes;

}
