package ru.dolzhenkoms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateArticleResponseDto {

    private Long articleId;

    private String name;
}
