package de.fmm.recipestore.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.dto.RecipeIngredientDto;
import de.fmm.recipestore.domain.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    public RecipeDto map(final Recipe recipe) {
        final RecipeDto recipeDto = objectMapper.convertValue(recipe, RecipeDto.class);

        final List<RecipeIngredientDto> recipeIngredientDtos = recipe.getRecipeIngredients().stream()
                .map(recipeIngredient -> objectMapper.convertValue(recipeIngredient, RecipeIngredientDto.class))
                .toList();
        recipeDto.setIngredients(recipeIngredientDtos);

        return recipeDto;
    }

}
