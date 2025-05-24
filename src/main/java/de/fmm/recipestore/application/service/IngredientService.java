package de.fmm.recipestore.application.service;

import de.fmm.recipestore.application.dto.IngredientDto;
import de.fmm.recipestore.application.mapper.IngredientMapper;
import de.fmm.recipestore.domain.entity.Cookbook;
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
    private final IngredientMapper ingredientMapper;

    public List<IngredientDto> getIngredients(final Long cookbookId) {
        final List<Ingredient> ingredients = ingredientRepository.findAllByCookbook_Id(cookbookId);
        return ingredients.stream()
                .map(ingredientMapper::map)
                .toList();
    }

    public IngredientDto createNewIngredient(@RequestBody final IngredientDto ingredientDto) {
        final Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        if (ingredientDto.getCookbookId() != null) {
            final Cookbook cookbook = new Cookbook();
            cookbook.setId(ingredientDto.getCookbookId());
            ingredient.setCookbook(cookbook);
        }
        final Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return ingredientMapper.map(savedIngredient);
    }

}
