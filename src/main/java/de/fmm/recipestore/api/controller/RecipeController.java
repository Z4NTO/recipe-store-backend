package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.domain.entity.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @GetMapping
    public List<Recipe> getAllRecipes() {
        final Recipe recipe = new Recipe();
        recipe.setTitle("Spaghetti Carbonara");
        return List.of(recipe);
    }

}
