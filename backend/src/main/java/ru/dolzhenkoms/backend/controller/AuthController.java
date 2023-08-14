package ru.dolzhenkoms.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dolzhenkoms.backend.dto.JwtAuthTokenDto;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.service.AuthService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(
            @RequestBody UserRegisterRequestDto userRegisterRequestDto
    ) {
        return authService.createNewUser(userRegisterRequestDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(
            @RequestBody JwtAuthTokenDto jwtAuthTokenDto
    ) {
        return authService.createAuthToken(jwtAuthTokenDto);
    }

    @GetMapping("/info")
    public String getUsername(Principal principal) {
        return principal.getName();
    }
}
