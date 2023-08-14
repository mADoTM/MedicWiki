package ru.dolzhenkoms.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dolzhenkoms.backend.dto.ArticleDetailsDto;
import ru.dolzhenkoms.backend.dto.ArticleFeedDto;
import ru.dolzhenkoms.backend.dto.CreateArticleRequestDto;
import ru.dolzhenkoms.backend.dto.CreateArticleResponseDto;
import ru.dolzhenkoms.backend.service.ArticleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/actual")
    public ResponseEntity<List<ArticleFeedDto>> getActualArticleFeeds() {
        return ResponseEntity.ok(articleService.getArticleFeeds());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ArticleDetailsDto> getArticleDetailsDto(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleDetailsById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CreateArticleResponseDto> createArticle(@RequestBody CreateArticleRequestDto request) {
        return ResponseEntity.ok(articleService.createArticle(request));
    }
}
