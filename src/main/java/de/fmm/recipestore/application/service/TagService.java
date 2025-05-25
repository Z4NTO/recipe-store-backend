package de.fmm.recipestore.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.domain.entity.Tag;
import de.fmm.recipestore.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final ObjectMapper objectMapper;

    public List<TagDto> getTags(final Long cookbookId) {
        final List<Tag> tags = tagRepository.findAllByCookbookId(cookbookId);
        return tags.stream()
                .map(tag -> objectMapper.convertValue(tag, TagDto.class))
                .toList();
    }

    public TagDto createNewTag(@RequestBody final TagDto tagDto) {
        final Tag tag = objectMapper.convertValue(tagDto, Tag.class);
        final Tag savedTag = tagRepository.save(tag);
        return objectMapper.convertValue(savedTag, TagDto.class);
    }

}
