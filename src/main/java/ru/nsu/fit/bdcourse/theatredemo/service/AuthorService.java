package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.repository.AuthorRepository;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> getAllAuthors(Integer pageNo, Integer pageSize, String sortBy,
                                      String name, String country, String gender) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("country", contains().ignoreCase())
                .withMatcher("gender", contains().ignoreCase());
        Author authorToFind = Author
                .builder()
                .name(name)
                .country(country)
                .gender(gender)
                .build();
        return authorRepository.findAll(Example.of(authorToFind, matcher), paging);
    }


    public ResponseEntity<Author> getAuthorById(Long id) {
        Optional<Author> authorData = authorRepository.findById(id);
        return authorData.map(actor ->
                new ResponseEntity<>(actor, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Author> createNewAuthor(Author author) {
        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Author> updateAuthor(Author author) {
        Optional<Author> authorData = authorRepository.findById(author.getId());
        if (authorData.isPresent()) {
            Author updatedAuthor = authorData.get();
            if (author.getName() != null) {
                updatedAuthor.setName(author.getName());
            }
            if (author.getCountry() != null) {
                updatedAuthor.setCountry(author.getCountry());
            }
            if (author.getGender() != null) {
                updatedAuthor.setGender(author.getGender());
            }
            return new ResponseEntity<>(authorRepository.save(updatedAuthor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAuthorById(Long id) {
        Optional<Author> authorToDelete = authorRepository.findById(id);
        if (authorToDelete.isPresent()) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
