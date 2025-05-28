package de.fmm.recipestore.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ObjectMapper objectMapper;

    public List<IngredientDto> getIngredients(final Long cookbookId) {
        final List<Ingredient> ingredients = ingredientRepository.findAllByCookbookId(cookbookId);
        return ingredients.stream()
                .map(ingredient
                        -> objectMapper.convertValue(ingredient, IngredientDto.class))
                .toList();
    }

    public Ingredient mergeWithExistingIngredients(final Ingredient newIngredient, final List<Ingredient> savedIngredients) {
        if (newIngredient == null) {
            return null;
        }
        return Optional.ofNullable(newIngredient.getId())
                .flatMap(ingredientRepository::findById)
                .map(existingIngredient -> updateExistingIngredient(newIngredient, existingIngredient))
                .orElseGet(() -> getSavedIngredientWithEqualNameOrCreateNew(newIngredient, savedIngredients));
    }

    private Ingredient getSavedIngredientWithEqualNameOrCreateNew(final Ingredient newIngredient, final List<Ingredient> savedIngredients) {
        return savedIngredients.stream()
                .filter(savedIngredient -> savedIngredient.getName().equals(newIngredient.getName()))
                .findAny()
                .orElseGet(() -> {
                    final Ingredient savedIngredient = ingredientRepository.save(newIngredient);
                    savedIngredients.add(savedIngredient);
                    return savedIngredient;
                });
    }

    private static Ingredient updateExistingIngredient(final Ingredient newIngredient, final Ingredient existingIngredient) {
        BeanUtils.copyProperties(newIngredient, existingIngredient);
        return existingIngredient;
    }

}
