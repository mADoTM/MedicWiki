package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserArticleDto {

    private String login;

    private String name;

    private String surname;
}
