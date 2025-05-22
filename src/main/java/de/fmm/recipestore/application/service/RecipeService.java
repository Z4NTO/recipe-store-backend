package de.fmm.recipestore.application.service;

import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.mapper.RecipeMapper;
import de.fmm.recipestore.domain.entity.Recipe;
import de.fmm.recipestore.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDto> getRecipes(final Long cookbookId) {
        final List<Recipe> recipes = recipeRepository.getRecipesByCookbook_Id(cookbookId);
        return recipes.stream()
                .map(recipeMapper::map)
                .toList();
    }

}
