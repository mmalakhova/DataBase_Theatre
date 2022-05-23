package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.News;
import ru.nsu.fit.bdcourse.theatredemo.model.NewsData;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "select n.data from News n")
    List<NewsData> getAllData();
}
