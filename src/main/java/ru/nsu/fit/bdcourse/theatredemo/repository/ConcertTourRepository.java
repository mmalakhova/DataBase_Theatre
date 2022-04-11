package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;

public interface ConcertTourRepository extends JpaRepository<ConcertTour, Long> {
}
