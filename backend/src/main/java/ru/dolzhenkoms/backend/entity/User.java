package ru.dolzhenkoms.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_user")
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String login;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false
    )
    private String name;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private LocalDate birthday;

    @OneToMany(
            mappedBy = "user"
    )
    @ToString.Exclude
    private List<Education> gradeList;

    @OneToMany(
            mappedBy = "author"
    )
    @ToString.Exclude
    private List<Article> articles;

    @ManyToMany(
            mappedBy = "contributors"
    )
    @ToString.Exclude
    private List<Article> contributedArticles;

    @OneToMany(
            mappedBy = "contributor"
    )
    @ToString.Exclude
    private List<Contribute> contributes;
}
