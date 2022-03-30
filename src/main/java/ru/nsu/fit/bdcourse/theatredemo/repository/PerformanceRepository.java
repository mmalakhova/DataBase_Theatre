package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;

import javax.transaction.Transactional;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findPerformancesByDate(String date);
    List<Performance> findPerformancesByTitle(String title);

    List<Performance> findPerformancesByActorsId(Long actorId);

    List<Performance> findByAuthorId(Long authorId);

    @Transactional
    void deleteByAuthorId(Long authorId);

}
