package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    @ToString.Exclude
    private Performance performance;

    @Column(name = "price")
    private Integer price;

    @Column(name = "row")
    private Integer row;

    @Column(name = "seat")
    private Integer seat;

    @Column(name = "is_season_ticket")
    private Boolean isSeasonTicket;

}
