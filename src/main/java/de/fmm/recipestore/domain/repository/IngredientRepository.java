package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
