package de.fmm.recipestore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fmm.recipestore.application.dto.CookbookDto;
import de.fmm.recipestore.domain.repository.CookbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cookbook")
@RequiredArgsConstructor
public class CookbookController {

    private final CookbookRepository cookbookRepository;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<CookbookDto> getAllCookbooks() {
        return cookbookRepository.findAll().stream()
                .map(cookbook -> objectMapper.convertValue(cookbook, CookbookDto.class))
                .toList();
    }

}
