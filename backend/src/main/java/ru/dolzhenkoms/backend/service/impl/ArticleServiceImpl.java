package ru.dolzhenkoms.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dolzhenkoms.backend.dto.*;
import ru.dolzhenkoms.backend.entity.Article;
import ru.dolzhenkoms.backend.entity.MedicTerm;
import ru.dolzhenkoms.backend.entity.User;
import ru.dolzhenkoms.backend.repository.ArticleRepository;
import ru.dolzhenkoms.backend.service.ArticleService;
import ru.dolzhenkoms.backend.service.UserService;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    @Override
    public List<ArticleFeedDto> getArticleFeeds() {
        return articleRepository
                .findAll()
                .stream()
                .map(this::mapArticleToArticleFeed)
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDetailsDto getArticleDetailsById(Long id) {
        final var articleFromDb = articleRepository.findArticleById(id);

        if (articleFromDb == null) {
            throw new EntityNotFoundException("The wrong article's name");
        }

        return getArticleDetailsDto(articleFromDb);
    }

    @Override
    public CreateArticleResponseDto createArticle(CreateArticleRequestDto requestDto) {
        if(articleRepository.findArticleByName(requestDto.getName()) != null) {
            throw new EntityExistsException("There is an article with the same name");
        }

        final var userOptional = userService.findById(requestDto.getUserId());

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException("Wrong user details");
        }

        var persistArticle = mapCreateArticleRequestDtoToArticle(requestDto);
        persistArticle.setAuthor(userOptional.get());
        persistArticle.setViewsCount(0);

        var savedArticle = articleRepository.save(persistArticle);

        return new CreateArticleResponseDto(savedArticle.getId(), savedArticle.getName());
    }

    private ArticleDetailsDto getArticleDetailsDto(Article articleFromDb) {
        return ArticleDetailsDto.builder()
                .author(
                        mapUserToUserArticleDto(
                                articleFromDb.getAuthor()
                        )
                )
                .contributors(
                        articleFromDb
                                .getContributors()
                                .stream()
                                .map(this::mapUserToUserArticleDto)
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

    private UserArticleDto mapUserToUserArticleDto(final User user) {
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

    private MedicTerm mapDtoToMedicTerm(final MedicTermDto medicTerm) {
        return MedicTerm.builder()
                .name(medicTerm.getName())
                .history(medicTerm.getHistory())
                .application(medicTerm.getApplication())
                .photo(medicTerm.getPhoto())
                .build();
    }

    private Article mapCreateArticleRequestDtoToArticle(final CreateArticleRequestDto dto) {
        return Article.builder()
                .name(dto.getName())
                .medicTerm(mapDtoToMedicTerm(dto.getMedicTerm()))
                .createdAt(LocalDate.now())
                .build();
    }
}
