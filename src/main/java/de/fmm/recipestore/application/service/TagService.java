package de.fmm.recipestore.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.domain.entity.Tag;
import de.fmm.recipestore.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Tag> mergeWithExistingTags(final List<Tag> newAndUpdatedTags) {
        return newAndUpdatedTags.stream()
                .map(newTag -> Optional.ofNullable(newTag)
                        .map(Tag::getId)
                        .flatMap(tagRepository::findById)
                        .map(existingTag -> {
                            BeanUtils.copyProperties(newTag, existingTag);
                            return existingTag;
                        })
                        .orElse(newTag))
                .toList();
    }

}
