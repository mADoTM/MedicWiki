package ru.dolzhenkoms.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.entity.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User createNewUser(UserRegisterRequestDto userRegisterRequestDto);

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);
}
