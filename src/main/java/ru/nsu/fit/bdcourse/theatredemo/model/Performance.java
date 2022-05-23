package ru.nsu.fit.bdcourse.theatredemo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Ticket> ticketSet;

    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Role> roleSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_tour_id")
    @ToString.Exclude
    private ConcertTour concertTour;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "musicians_and_performances",
            joinColumns = @JoinColumn(name = "performance_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "musician_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore
    private Set<Musician> musicians = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "actors_and_performances",
                joinColumns = @JoinColumn(name = "performance_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "performances_and_stage_directors",
                joinColumns = @JoinColumn(name = "performance_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "stage_director_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore
    private Set<StageDirector> stageDirectors = new HashSet<>();

    @Column(name = "genre")
    private String genre;

    @Column(name = "age_rating")
    private String ageRating;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "descr")
    private String descr;


}
