package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.Musician;

public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
