package de.fmm.recipestore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.api.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        final List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream()
                .map(tag -> objectMapper.convertValue(tag, IngredientDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public IngredientDto createIngredient(final @RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = objectMapper.convertValue(ingredientDto, Ingredient.class);
        ingredient = ingredientRepository.save(ingredient);
        return objectMapper.convertValue(ingredient, IngredientDto.class);
    }

}
