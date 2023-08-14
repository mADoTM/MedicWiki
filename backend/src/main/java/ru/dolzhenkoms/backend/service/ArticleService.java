package ru.dolzhenkoms.backend.service;

import ru.dolzhenkoms.backend.dto.ArticleDetailsDto;
import ru.dolzhenkoms.backend.dto.ArticleFeedDto;
import ru.dolzhenkoms.backend.dto.CreateArticleRequestDto;
import ru.dolzhenkoms.backend.dto.CreateArticleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<ArticleFeedDto> getArticleFeeds();

    ArticleDetailsDto getArticleDetailsById(Long id);

    CreateArticleResponseDto createArticle(CreateArticleRequestDto requestDto);
}
