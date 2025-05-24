package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByCookbook_Id(Long cookbookId);

}
