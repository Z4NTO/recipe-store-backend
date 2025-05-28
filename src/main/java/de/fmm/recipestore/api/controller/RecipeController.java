package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.RecipeDto;
import de.fmm.recipestore.application.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public RecipeDto createOrUpdateRecipe(@RequestBody final RecipeDto recipeDto) {
        return recipeService.createOrUpdateRecipe(recipeDto);
    }

}
