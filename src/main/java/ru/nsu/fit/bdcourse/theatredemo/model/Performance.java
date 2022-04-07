package ru.nsu.fit.bdcourse.theatredemo.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Ticket> ticketSet;

    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Role> roleSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_tour_id")
    @ToString.Exclude
    private ConcertTour concertTour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "musicians",
            joinColumns = @JoinColumn(name = ""))

    @Column(name = "genre")
    private String genre;

    @Column(name = "age_rating")
    private String ageRating;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_premiere")
    private Boolean isPremiere;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

}
