package de.fmm.recipestore.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private String title;
    private List<TagDto> tags;
    private List<RecipeIngredientDto> ingredients;
    private String notes;

}
