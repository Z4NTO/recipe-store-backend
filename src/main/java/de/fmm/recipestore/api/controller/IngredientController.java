package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.application.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("{cookbookId}")
    public List<IngredientDto> getIngredientsByCookbookId(@PathVariable final Long cookbookId) {
        return ingredientService.getIngredients(cookbookId);
    }

}
