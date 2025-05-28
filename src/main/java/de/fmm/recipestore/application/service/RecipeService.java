package de.fmm.recipestore.application.service;

import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.mapper.RecipeMapper;
import de.fmm.recipestore.domain.entity.Recipe;
import de.fmm.recipestore.domain.entity.RecipeIngredient;
import de.fmm.recipestore.domain.entity.Tag;
import de.fmm.recipestore.domain.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final TagService tagService;
    private final RecipeIngredientService recipeIngredientService;

    public List<RecipeDto> getRecipes(final Long cookbookId) {
        final List<Recipe> recipes = recipeRepository.getRecipesByCookbook_Id(cookbookId);
        return recipes.stream()
                .map(recipeMapper::map)
                .toList();
    }

    @Transactional
    public RecipeDto createOrUpdateRecipe(final RecipeDto recipeDto) {
        final Recipe recipe = recipeMapper.map(recipeDto);

        final List<Tag> mergedTags = tagService.mergeWithExistingTags(recipe.getTags());
        recipe.setTags(mergedTags);

        final List<RecipeIngredient> mergedRecipeIngredients = recipeIngredientService.mergeWithExistingRecipeIngredients(recipe.getRecipeIngredients());
        recipe.setRecipeIngredients(mergedRecipeIngredients);

        final Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.map(savedRecipe);
    }

}
