package de.fmm.recipestore.domain.repository;

import de.fmm.recipestore.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByCookbook_Id(Long cookbookId);

}
