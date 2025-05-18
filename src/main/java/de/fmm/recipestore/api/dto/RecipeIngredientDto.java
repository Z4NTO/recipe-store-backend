package de.fmm.recipestore.api.dto;

import lombok.Data;

@Data
public class RecipeIngredientDto {
    private IngredientDto ingredient;
    private String amount;
}
