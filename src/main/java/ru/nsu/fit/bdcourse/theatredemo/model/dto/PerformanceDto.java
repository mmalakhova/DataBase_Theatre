package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.bdcourse.theatredemo.model.*;

@Getter
@Setter
@ToString
public class PerformanceDto {

    private Long id;

    private String name;

    private String authorName;

    private String concertTourName;

    private String genre;

    private String ageRating;

    private String date;

    private String descr;

   public PerformanceDto(Performance performance) {
       this.id = performance.getId();
       this.name = performance.getName();
       this.authorName = performance.getAuthor().getName();
       this.concertTourName = performance.getConcertTour().getTitle();
       this.genre = performance.getGenre();
       this.ageRating = performance.getAgeRating();
       this.date = performance.getDate().toString();
       this.descr = performance.getDescr();
   }

}
