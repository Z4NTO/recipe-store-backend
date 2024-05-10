package de.fmm.recipestore.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RecipeDto {

    private Long id;

    private String title;

    private Set<TagDto> tags;

    private Set<IngredientDto> ingredients;

    private String notes;

}
