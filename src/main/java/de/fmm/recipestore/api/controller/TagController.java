package de.fmm.recipestore.api.controller;

import de.fmm.recipestore.api.dto.TagDto;
import de.fmm.recipestore.domain.entity.Tag;
import de.fmm.recipestore.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<TagDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public TagDto createTag(@RequestBody TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        tag = tagRepository.save(tag);
        return modelMapper.map(tag, TagDto.class);
    }
}
