package ru.dolzhenkoms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolzhenkoms.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String name);
}