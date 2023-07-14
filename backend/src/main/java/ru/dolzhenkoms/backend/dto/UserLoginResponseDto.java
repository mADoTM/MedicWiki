package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDto {

    private String name;

    private String surname;
}
