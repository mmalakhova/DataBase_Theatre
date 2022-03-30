package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByName(String name);
}
