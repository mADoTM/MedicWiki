package ru.dolzhenkoms.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.dolzhenkoms.backend.dto.JwtResponseDto;
import ru.dolzhenkoms.backend.dto.UserRegisterResponseDto;
import ru.dolzhenkoms.backend.exception.AppError;
import ru.dolzhenkoms.backend.service.AuthService;
import ru.dolzhenkoms.backend.dto.JwtAuthTokenDto;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.service.UserService;
import ru.dolzhenkoms.backend.util.JwtTokenUtils;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ResponseEntity<?> createNewUser(UserRegisterRequestDto userRegisterRequestDto) {
        log.debug("Here");
        if (userService.findByLogin(userRegisterRequestDto.getLogin()).isPresent()) {
            return new ResponseEntity<>(new AppError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Пользователь с указанным именем уже существует"),
                    HttpStatus.BAD_REQUEST);
        }
        final var user = userService.createNewUser(userRegisterRequestDto);
        return ResponseEntity.ok(new UserRegisterResponseDto(
                user.getLogin(),
                user.getName()
                ));
    }

    @Override
    public ResponseEntity<?> createAuthToken(JwtAuthTokenDto jwtAuthTokenDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtAuthTokenDto.getLogin(),
                            jwtAuthTokenDto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Неправильный логин или пароль"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtAuthTokenDto.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
