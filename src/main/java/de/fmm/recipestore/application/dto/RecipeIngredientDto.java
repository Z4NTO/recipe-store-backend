package de.fmm.recipestore.application.dto;

import lombok.Data;

@Data
public class RecipeIngredientDto {
    private Long id;
    private IngredientDto ingredient;
    private String amount;
}
