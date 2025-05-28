package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

}
