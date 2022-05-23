package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
