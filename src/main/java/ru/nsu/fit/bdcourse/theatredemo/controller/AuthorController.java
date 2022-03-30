package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/theatre")
public class AuthorController {
    AuthorRepository authorRepository;

    private AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false)String name) {
        List<Author> authors = new ArrayList<>();
        if (name == null) {
            authors.addAll(authorRepository.findAll());
        } else {
            authors.addAll(authorRepository.findAllByName(name));
        }
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

}
