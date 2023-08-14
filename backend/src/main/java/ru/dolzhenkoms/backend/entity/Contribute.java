package ru.dolzhenkoms.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "contribute")
public class Contribute {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "article_id"
    )
    private Article article;

    @ManyToOne
    @JoinColumn(
            name = "contributor_id"
    )
    private User contributor;

    @Column
    private LocalDate createdAt;

    @Column
    @Enumerated(
            EnumType.STRING
    )
    private ContributeStatus status;
}