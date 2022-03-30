package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.repository.AuthorRepository;
import ru.nsu.fit.bdcourse.theatredemo.repository.PerformanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = )
@RestController
@RequestMapping("/theatre")
public class PerformanceController {

    private final PerformanceRepository performanceRepository;
    private final AuthorRepository authorRepository;

    public PerformanceController(PerformanceRepository performanceRepository,
                                 AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.performanceRepository = performanceRepository;
    }

    @GetMapping("/performances")
    public ResponseEntity<List<Performance>> getAllPerformances(@RequestParam(required = false) String title) {
        List<Performance> performances = new ArrayList<>();
        if (title == null) {
            performances.addAll(performanceRepository.findAll());
        } else {
            performances.addAll(performanceRepository.findPerformancesByTitle(title));
        }

        if (performances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(performances, HttpStatus.OK);
    }


    @GetMapping("/performances/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable("id") long id) {
        Optional<Performance> performanceData = performanceRepository.findById(id);
        return performanceData.map(performance ->
                new ResponseEntity<>(performance, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/performances")
    public ResponseEntity<Performance> createPerformance(@RequestBody Performance performance) {
        Performance createdPerformance = performanceRepository
                .save(new Performance(performance.getTitle(), performance.getDate(), performance.getDescription()));
        return  new ResponseEntity<>(createdPerformance, HttpStatus.CREATED);
    }

    @PutMapping("/performances/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable("id")long id,
                                                         @RequestBody Performance performance) {
        Optional<Performance> performanceData = performanceRepository.findById(id);
        if (performanceData.isPresent()) {
            Performance updatedPerformance = performanceData.get();
            updatedPerformance.setTitle(performance.getDate());
            updatedPerformance.setDate(performance.getDate());
            updatedPerformance.setDescription(performance.getDescription());
            return new ResponseEntity<>(performanceRepository.save(updatedPerformance), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/performances")
    public ResponseEntity<HttpStatus> deleteAllPerformances() {
        performanceRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/performances/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        performanceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/performances/{date}")
    public ResponseEntity<List<Performance>> findPerformancesByDate(@PathVariable("date") String date) {
        List<Performance> performances = performanceRepository.findPerformancesByDate(date);
        if (performances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(performances, HttpStatus.OK);
        }
    }

    //requests connected to authors table
    @GetMapping("/authors/{authorId}/performances")
    public ResponseEntity<List<Performance>> getAllPerformancesByAuthorId(@PathVariable(value = "authorId") Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Performance> performances = performanceRepository.findPerformancesByActorsId(authorId);
        return new ResponseEntity<>(performances, HttpStatus.OK);
    }
}
