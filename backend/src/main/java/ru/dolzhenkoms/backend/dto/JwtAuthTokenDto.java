package ru.dolzhenkoms.backend.dto;

import lombok.Data;

@Data
public class JwtAuthTokenDto {
    private String login;
    private String password;
}
