package ru.dolzhenkoms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolzhenkoms.backend.entity.MedicTerm;

public interface MedicTermRepository extends JpaRepository<MedicTerm, Long> {
}
