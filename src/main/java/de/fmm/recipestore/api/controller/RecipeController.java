package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("{cookbookId}")
    public List<RecipeDto> getRecipesByCookbookId(@PathVariable final Long cookbookId) {
        return recipeService.getRecipes(cookbookId);
    }

}
