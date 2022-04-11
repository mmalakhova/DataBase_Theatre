package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.StageDirector;

public interface StageDirectorRepository extends JpaRepository<StageDirector, Long> {
}
