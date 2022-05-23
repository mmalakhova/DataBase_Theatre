package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}
