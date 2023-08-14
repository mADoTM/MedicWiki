package ru.dolzhenkoms.backend.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @OneToOne
    @JoinColumn(
            name = "medic_term_id",
            nullable = false
    )
    private MedicTerm medicTerm;

    @Column
    private int viewsCount;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(
            name = "author_id"
    )
    private User author;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "article_contributors",
            joinColumns = @JoinColumn(name = "contributor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "article_id",
                    referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<User> contributors;

    @OneToMany(
            mappedBy = "article"
    )
    @ToString.Exclude
    private List<Contribute> contributes;
}