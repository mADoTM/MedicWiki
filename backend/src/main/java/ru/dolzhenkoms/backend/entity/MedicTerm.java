package ru.dolzhenkoms.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = { "id", "photo" })
public class MedicTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            unique = true
    )
    private String name;

    @Column(
            nullable = false
    )
    private String history;

    @Column(
            nullable = false
    )
    private String application;

    @Lob
    @Column(
            nullable = false
    )
    private byte[] photo;

    @OneToOne(
            mappedBy = "medicTerm"
    )
    private Article article;
}
