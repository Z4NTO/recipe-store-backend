package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
