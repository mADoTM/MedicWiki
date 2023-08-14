package ru.dolzhenkoms.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dolzhenkoms.backend.dto.UserRegisterRequestDto;
import ru.dolzhenkoms.backend.repository.UserRepository;
import ru.dolzhenkoms.backend.service.UserService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Username '%s' not found", username)
        ));

        //TODO update roles
        return new User(
                user.getLogin(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    @Override
    public ru.dolzhenkoms.backend.entity.User createNewUser(UserRegisterRequestDto userRegisterRequestDto) {
        final var newUser = new ru.dolzhenkoms.backend.entity.User();
        newUser.setLogin(userRegisterRequestDto.getLogin());
        newUser.setName(userRegisterRequestDto.getName());
        newUser.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public Optional<ru.dolzhenkoms.backend.entity.User> findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public Optional<ru.dolzhenkoms.backend.entity.User> findById(Long id) {
        return userRepository.findById(id);
    }
}
