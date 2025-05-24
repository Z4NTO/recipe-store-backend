package de.fmm.recipestore.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.domain.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapper {

    private final ObjectMapper objectMapper;

    public TagDto map(final Tag tag) {
        final TagDto tagDto = objectMapper.convertValue(tag, TagDto.class);
        if (tag.getCookbook() != null) {
            tagDto.setCookbookId(tag.getCookbook().getId());
        }
        return tagDto;
    }

}
