package de.fmm.recipestore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.api.dto.TagDto;
import de.fmm.recipestore.domain.entity.Tag;
import de.fmm.recipestore.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<TagDto> getAllTags() {
        final List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tag -> objectMapper.convertValue(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public TagDto createTag(@RequestBody final TagDto tagDto) {
        Tag tag = objectMapper.convertValue(tagDto, Tag.class);
        tag = tagRepository.save(tag);
        return objectMapper.convertValue(tag, TagDto.class);
    }
}
