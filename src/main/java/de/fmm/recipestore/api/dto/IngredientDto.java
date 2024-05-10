package de.fmm.recipestore.api.dto;

import lombok.Data;

@Data
public class IngredientDto {
    private Long id;
    private String name;
    private String amount;
}
