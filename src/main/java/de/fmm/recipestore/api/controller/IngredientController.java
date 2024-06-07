package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.api.dto.IngredientDto;
import de.fmm.recipestore.domain.entity.Ingredient;
import de.fmm.recipestore.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream()
                .map(tag -> modelMapper.map(tag, IngredientDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public IngredientDto createIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        ingredient = ingredientRepository.save(ingredient);
        return modelMapper.map(ingredient, IngredientDto.class);
    }

}
