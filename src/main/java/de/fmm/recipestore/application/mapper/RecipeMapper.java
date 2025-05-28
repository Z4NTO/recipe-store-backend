package de.fmm.recipestore.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.dto.RecipeIngredientDto;
import de.fmm.recipestore.domain.entity.Cookbook;
import de.fmm.recipestore.domain.entity.Recipe;
import de.fmm.recipestore.domain.entity.RecipeIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    public RecipeDto map(final Recipe recipe) {
        final RecipeDto recipeDto = objectMapper.convertValue(recipe, RecipeDto.class);

        if (recipe.getCookbook() != null) {
            recipeDto.setCookbookId(recipe.getCookbook().getId());
        }

        final List<RecipeIngredientDto> recipeIngredientDtos = recipe.getRecipeIngredients().stream()
                .map(recipeIngredient -> objectMapper.convertValue(recipeIngredient, RecipeIngredientDto.class))
                .toList();
        recipeDto.setIngredients(recipeIngredientDtos);

        return recipeDto;
    }

    public Recipe map(final RecipeDto recipeDto) {
        final Recipe recipe = objectMapper.convertValue(recipeDto, Recipe.class);

        final Cookbook cookbook = new Cookbook();
        cookbook.setId(recipeDto.getCookbookId());
        recipe.setCookbook(cookbook);

        final List<RecipeIngredient> recipeIngredients = Optional.ofNullable(recipeDto.getIngredients())
                .orElse(Collections.emptyList()).stream()
                .map(recipeIngredient -> objectMapper.convertValue(recipeIngredient, RecipeIngredient.class))
                .toList();
        recipe.setRecipeIngredients(recipeIngredients);

        return recipe;
    }

}
