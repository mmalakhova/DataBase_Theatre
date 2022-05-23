package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;

@Repository
public interface ConcertTourRepository extends JpaRepository<ConcertTour, Long> {

}
