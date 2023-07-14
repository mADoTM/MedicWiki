package ru.dolzhenkoms.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dolzhenkoms.backend.dto.ArticleDetailsDto;
import ru.dolzhenkoms.backend.dto.ArticleFeedDto;
import ru.dolzhenkoms.backend.dto.MedicTermDto;
import ru.dolzhenkoms.backend.dto.UserArticleDto;
import ru.dolzhenkoms.backend.entity.Article;
import ru.dolzhenkoms.backend.entity.MedicTerm;
import ru.dolzhenkoms.backend.entity.User;
import ru.dolzhenkoms.backend.repository.ArticleRepository;
import ru.dolzhenkoms.backend.service.ArticleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Override
    public List<ArticleFeedDto> getArticleFeeds() {
        return repository
                .findAll()
                .stream()
                .map(this::mapArticleToArticleFeed)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleDetailsDto> getArticleDetails(final String articleName) {
        final var articleFromDb = repository.findArticleByName(articleName);

        if (articleFromDb == null) {
            return Optional.empty();
        }

        final var dto = getArticleDetailsDto(articleFromDb);

        return Optional.of(dto);
    }

    private ArticleDetailsDto getArticleDetailsDto(Article articleFromDb) {
        return ArticleDetailsDto.builder()
                .author(
                        mapUserToArticleDto(
                                articleFromDb.getAuthor()
                        )
                )
                .contributors(
                        articleFromDb
                                .getContributors()
                                .stream()
                                .map(this::mapUserToArticleDto)
                                .toList()
                )
                .medicTerm(
                        mapMedicTermToDto(
                                articleFromDb.getMedicTerm()
                        )
                )
                .build();
    }

    private ArticleFeedDto mapArticleToArticleFeed(final Article article) {
        return ArticleFeedDto.builder()
                .name(article.getName())
                .createdAt(article.getCreatedAt())
                .viewsCount(article.getViewsCount())
                .build();
    }

    private UserArticleDto mapUserToArticleDto(final User user) {
        return UserArticleDto.builder()
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getLastname())
                .build();
    }

    private MedicTermDto mapMedicTermToDto(final MedicTerm medicTerm) {
        return MedicTermDto.builder()
                .name(medicTerm.getName())
                .application(medicTerm.getApplication())
                .history(medicTerm.getHistory())
                .photo(medicTerm.getPhoto())
                .build();
    }
}
