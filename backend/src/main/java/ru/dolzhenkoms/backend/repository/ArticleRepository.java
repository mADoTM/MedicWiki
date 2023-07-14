package ru.dolzhenkoms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolzhenkoms.backend.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleByName(String name);
}