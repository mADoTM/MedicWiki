package ru.dolzhenkoms.backend.service;

import ru.dolzhenkoms.backend.dto.UserLoginRequestDto;
import ru.dolzhenkoms.backend.dto.UserLoginResponseDto;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> registerNewUser(UserRegisterRequestDto user);

    Optional<UserLoginResponseDto> loginUser(UserLoginRequestDto user);
}
