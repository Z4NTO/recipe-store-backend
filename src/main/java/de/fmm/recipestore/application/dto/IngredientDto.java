package de.fmm.recipestore.application.dto;

import lombok.Data;

@Data
public class IngredientDto {
    private Long id;
    private String name;
    private Long cookbookId;
}
