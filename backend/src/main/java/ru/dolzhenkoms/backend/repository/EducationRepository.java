package ru.dolzhenkoms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolzhenkoms.backend.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
}