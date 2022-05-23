package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PerformanceCreateRequest implements Serializable {

    private Long id;
    private String name;
    private String genre;
    private String ageRating;
    private String date;
    private String descr;
    private String author;
    private String concertTour;
}
