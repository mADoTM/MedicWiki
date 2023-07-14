package ru.dolzhenkoms.backend.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String login;

    private String password;

    private String name;
}
