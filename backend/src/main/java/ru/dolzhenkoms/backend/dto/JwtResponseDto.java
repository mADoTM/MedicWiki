package ru.dolzhenkoms.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponseDto {
    private final String token;
}
