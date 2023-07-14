package ru.dolzhenkoms.backend.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String login;

    private String password;
}
