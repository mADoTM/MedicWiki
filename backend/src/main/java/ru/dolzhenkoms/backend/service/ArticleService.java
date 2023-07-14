package ru.dolzhenkoms.backend.service;

import ru.dolzhenkoms.backend.dto.ArticleDetailsDto;
import ru.dolzhenkoms.backend.dto.ArticleFeedDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<ArticleFeedDto> getArticleFeeds();

    Optional<ArticleDetailsDto> getArticleDetails(String articleName);
}
