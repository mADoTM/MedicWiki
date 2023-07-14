package ru.dolzhenkoms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolzhenkoms.backend.entity.Contribute;

public interface ContributeRepository extends JpaRepository<Contribute, Long> {
}