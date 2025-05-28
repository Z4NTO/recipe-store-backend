// File: src/main/java/de/fmm/recipestore/api/controller/TagController.java
package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.application.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<TagDto> getTagsByCookbookId(@RequestParam final Long cookbookId) {
        return tagService.getTags(cookbookId);
    }

}