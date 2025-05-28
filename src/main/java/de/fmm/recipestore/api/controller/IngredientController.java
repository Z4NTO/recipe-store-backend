package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.application.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> getIngredientsByCookbookId(@RequestParam final Long cookbookId) {
        return ingredientService.getIngredients(cookbookId);
    }

}
