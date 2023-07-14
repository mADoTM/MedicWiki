package ru.dolzhenkoms.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dolzhenkoms.backend.dto.UserLoginRequestDto;
import ru.dolzhenkoms.backend.dto.UserLoginResponseDto;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.entity.User;
import ru.dolzhenkoms.backend.repository.UserRepository;
import ru.dolzhenkoms.backend.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> registerNewUser(UserRegisterRequestDto user) {
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            return Optional.empty();
        }

        final var newUser = User.builder()
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();

        userRepository.save(newUser);

        return Optional.of(newUser);
    }

    @Override
    public Optional<UserLoginResponseDto> loginUser(UserLoginRequestDto user) {
        final var userFromDb = userRepository.findUserByLogin(user.getLogin());

        if (userFromDb == null) {
            return Optional.empty();
        }

        if (!userFromDb.getPassword().equals(user.getPassword())) {
            return Optional.empty();
        }

        final var response = UserLoginResponseDto
                .builder()
                .name(userFromDb.getName())
                .surname(userFromDb.getLastname())
                .build();

        return Optional.of(response);
    }
}
