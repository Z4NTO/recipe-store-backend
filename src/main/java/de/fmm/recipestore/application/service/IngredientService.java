package de.fmm.recipestore.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public IngredientDto createNewIngredient(@RequestBody final IngredientDto ingredientDto) {
        final Ingredient ingredient = objectMapper.convertValue(ingredientDto, Ingredient.class);
        final Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return objectMapper.convertValue(savedIngredient, IngredientDto.class);
    }

}
