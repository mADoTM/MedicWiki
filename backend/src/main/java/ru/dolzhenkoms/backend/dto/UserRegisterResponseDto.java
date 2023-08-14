package ru.dolzhenkoms.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterResponseDto {
    private String login;

    private String name;
}
