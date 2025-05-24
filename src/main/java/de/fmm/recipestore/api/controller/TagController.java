// File: src/main/java/de/fmm/recipestore/api/controller/TagController.java
package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.application.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("{cookbookId}")
    public List<TagDto> getRecipesByCookbookId(@PathVariable final Long cookbookId) {
        return tagService.getTags(cookbookId);
    }

    @PutMapping
    public TagDto createNewTag(@RequestBody final TagDto tagDto) {
        return tagService.createNewTag(tagDto);
    }
}