package ru.dolzhenkoms.backend.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private LocalDate startedAt;

    @Column
    private LocalDate endedAt;

    @Column
    private String educationalInstitution;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}