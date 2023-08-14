package ru.dolzhenkoms.backend.service;

import org.springframework.http.ResponseEntity;
import ru.dolzhenkoms.backend.dto.JwtAuthTokenDto;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;

public interface AuthService {
    ResponseEntity<?> createNewUser(UserRegisterRequestDto userRegisterRequestDto);

    ResponseEntity<?> createAuthToken(JwtAuthTokenDto jwtAuthTokenDto);
}
