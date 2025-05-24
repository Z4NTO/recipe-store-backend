package de.fmm.recipestore.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    private final ObjectMapper objectMapper;

    public IngredientDto map(final Ingredient ingredient) {
        final IngredientDto ingredientDto = objectMapper.convertValue(ingredient, IngredientDto.class);
        if (ingredient.getCookbook() != null) {
            ingredientDto.setCookbookId(ingredient.getCookbook().getId());
        }
        return ingredientDto;
    }

}
