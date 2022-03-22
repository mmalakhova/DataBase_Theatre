package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.Actor;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findActorByPerformancesId(Long performanceId);
}
