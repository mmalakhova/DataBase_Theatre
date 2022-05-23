package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.repository.AuthorRepository;

import ru.nsu.fit.bdcourse.theatredemo.service.AuthorService;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class AuthorController {

    private final AuthorService authorService;

    private AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public Page<Author> getAllAuthors(Integer pageNo, Integer pageSize, String sortBy,
                                      @RequestParam(name = "name") String name,
                                      @RequestParam(name = "country") String country,
                                      @RequestParam(name = "gender") String gender) {
       return authorService.getAllAuthors(pageNo, pageSize, sortBy, name, country, gender);
    }


    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return authorService.createNewAuthor(author);
    }

    @RequestMapping(value = "/authors", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }


    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable("id") Long id) {
        return authorService.deleteAuthorById(id);
    }

}
