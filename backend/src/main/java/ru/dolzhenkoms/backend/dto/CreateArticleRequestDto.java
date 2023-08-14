package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateArticleRequestDto {
    private Long userId;

    private String name;

    private MedicTermDto medicTerm;
}
