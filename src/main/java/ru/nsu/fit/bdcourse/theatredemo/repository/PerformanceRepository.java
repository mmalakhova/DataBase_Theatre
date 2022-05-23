package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;


@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query(nativeQuery = true,
            value = "select a.* from authors a inner join performances p on a.id = p.author_id " +
                    "where a.name = ?1")
    Author findAuthorByName(String authorName);

    @Query(nativeQuery = true,
            value = "select c.* from concert_tours c inner join performances p on c.id = p.concert_tour_id " +
                    "where c.title = ?1")
    ConcertTour findConcertTourByName(String concertTourName);

    @Query(nativeQuery = true, value = "select genre from performances group by genre " +
            "having count(*) >= all (select count(*) from performances group by genre)")
    String findMostPopularGenre();

}
