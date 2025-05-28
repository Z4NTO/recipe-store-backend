package de.fmm.recipestore.application.service;

import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.entity.RecipeIngredient;
import de.fmm.recipestore.domain.repository.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientService ingredientService;

    public List<RecipeIngredient> mergeWithExistingRecipeIngredients(final List<RecipeIngredient> recipeIngredients) {
        final List<Ingredient> savedIngredients = new ArrayList<>();
        return recipeIngredients.stream()
                .filter(Objects::nonNull)
                .map(newRecipeIngredient -> Optional.ofNullable(newRecipeIngredient.getId())
                        .flatMap(recipeIngredientRepository::findById)
                        .map(existingRecipeIngredient -> updateExistingRecipeIngredient(newRecipeIngredient, existingRecipeIngredient, savedIngredients))
                        .orElseGet(() -> {
                            final Ingredient mergedIngredient = ingredientService.mergeWithExistingIngredients(newRecipeIngredient.getIngredient(), savedIngredients);
                            newRecipeIngredient.setIngredient(mergedIngredient);
                            return newRecipeIngredient;
                        })
                )
                .toList();
    }

    private RecipeIngredient updateExistingRecipeIngredient(final RecipeIngredient newRecipeIngredient,
                                                            final RecipeIngredient existingRecipeIngredient,
                                                            final List<Ingredient> savedIngredients) {
        BeanUtils.copyProperties(newRecipeIngredient, existingRecipeIngredient);

        final Ingredient mergedIngredient = ingredientService.mergeWithExistingIngredients(newRecipeIngredient.getIngredient(), savedIngredients);
        existingRecipeIngredient.setIngredient(mergedIngredient);

        return existingRecipeIngredient;
    }
}
