package de.fmm.recipestore.application.service;

import de.fmm.recipestore.application.dto.TagDto;
import de.fmm.recipestore.application.mapper.TagMapper;
import de.fmm.recipestore.domain.entity.Cookbook;
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
    private final TagMapper tagMapper;

    public List<TagDto> getTags(final Long cookbookId) {
        final List<Tag> tags = tagRepository.findAllByCookbook_Id(cookbookId);
        return tags.stream()
                .map(tagMapper::map)
                .toList();
    }

    public TagDto createNewTag(@RequestBody final TagDto tagDto) {
        final Tag tag = new Tag();
        tag.setName(tagDto.getName());
        if (tagDto.getCookbookId() != null) {
            final Cookbook cookbook = new Cookbook();
            cookbook.setId(tagDto.getCookbookId());
            tag.setCookbook(cookbook);
        }
        final Tag savedTag = tagRepository.save(tag);

        return tagMapper.map(savedTag);
    }

}
