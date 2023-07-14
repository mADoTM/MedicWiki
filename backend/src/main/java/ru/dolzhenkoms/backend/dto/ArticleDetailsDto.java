package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleDetailsDto {

    private UserArticleDto author;

    private MedicTermDto medicTerm;

    private List<UserArticleDto> contributors;
}
