package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.Cookbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Cookbook, Long> {

}
