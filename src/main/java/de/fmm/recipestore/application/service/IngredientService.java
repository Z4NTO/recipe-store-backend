package de.fmm.recipestore.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.entity.RecipeIngredient;
import de.fmm.recipestore.domain.repository.IngredientRepository;
import de.fmm.recipestore.domain.repository.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ObjectMapper objectMapper;

    public List<IngredientDto> getIngredients(final Long cookbookId) {
        final List<Ingredient> ingredients = ingredientRepository.findAllByCookbookId(cookbookId);
        return ingredients.stream()
                .map(ingredient
                        -> objectMapper.convertValue(ingredient, IngredientDto.class))
                .toList();
    }

    public List<RecipeIngredient> mergeWithExistingRecipeIngredients(final List<RecipeIngredient> recipeIngredients) {
        final Map<Long, Ingredient> mergedIngredients = new HashMap<>();
        return recipeIngredients.stream()
                .filter(Objects::nonNull)
                .map(newRecipeIngredient -> Optional.ofNullable(newRecipeIngredient.getId())
                        .flatMap(recipeIngredientRepository::findById)
                        .map(existingRecipeIngredient -> updateExistingRecipeIngredient(newRecipeIngredient, existingRecipeIngredient, mergedIngredients))
                        .orElseGet(() -> {
                            final Ingredient mergedIngredient = mergeWithExistingIngredients(newRecipeIngredient.getIngredient(), mergedIngredients);
                            newRecipeIngredient.setIngredient(mergedIngredient);
                            return newRecipeIngredient;
                        })
                )
                .toList();
    }

    private RecipeIngredient updateExistingRecipeIngredient(final RecipeIngredient newRecipeIngredient,
                                                            final RecipeIngredient existingRecipeIngredient,
                                                            final Map<Long, Ingredient> mergedIngredients) {
        BeanUtils.copyProperties(newRecipeIngredient, existingRecipeIngredient);

        final Ingredient mergedIngredient = mergeWithExistingIngredients(newRecipeIngredient.getIngredient(), mergedIngredients);
        existingRecipeIngredient.setIngredient(mergedIngredient);

        return existingRecipeIngredient;
    }

    private Ingredient mergeWithExistingIngredients(final Ingredient newIngredient, final Map<Long, Ingredient> mergedIngredients) {
        if (newIngredient == null) {
            return null;
        }
        return Optional.ofNullable(newIngredient.getId())
                .flatMap(ingredientRepository::findById)
                .map(existingIngredient -> updateExistingIngredient(newIngredient, existingIngredient, mergedIngredients))
                .orElseGet(() -> mergedIngredients.values().stream()
                        .filter(savedIngredient -> savedIngredient.getName().equals(newIngredient.getName()))
                        .findAny()
                        .orElseGet(() -> createNewIngredient(newIngredient, mergedIngredients))
                );
    }

    private static Ingredient updateExistingIngredient(final Ingredient newIngredient, final Ingredient existingIngredient, final Map<Long, Ingredient> mergedIngredients) {
        BeanUtils.copyProperties(newIngredient, existingIngredient);
        mergedIngredients.put(existingIngredient.getId(), existingIngredient);
        return existingIngredient;
    }

    private Ingredient createNewIngredient(final Ingredient newIngredient, final Map<Long, Ingredient> mergedIngredients) {
        final Ingredient savedIngredient = ingredientRepository.save(newIngredient);
        mergedIngredients.put(savedIngredient.getId(), savedIngredient);
        return savedIngredient;
    }

}
