package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.repository.PerformanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = )
@RestController
@RequestMapping("/theatre")
public class PerformanceController {

    private final PerformanceRepository repository;

    public PerformanceController(PerformanceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/performances")
    public ResponseEntity<List<Performance>> getAllPerformances(@RequestParam(required = false) String title) {
        List<Performance> performances = new ArrayList<>();
        if (title == null) {
            performances.addAll(repository.findAll());
        } else {
            performances.addAll(repository.findPerformancesByTitle(title));
        }

        if (performances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(performances, HttpStatus.OK);
    }


    @GetMapping("/performances/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable("id") long id) {
        Optional<Performance> performanceData = repository.findById(id);
        return performanceData.map(performance ->
                new ResponseEntity<>(performance, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/performances")
    public ResponseEntity<Performance> createPerformance(@RequestBody Performance performance) {
        Performance createdPerformance = repository
                .save(new Performance(performance.getTitle(), performance.getDate(), performance.getDescription()));
        return  new ResponseEntity<>(createdPerformance, HttpStatus.CREATED);
    }

    @PutMapping("/performances/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable("id")long id,
                                                         @RequestBody Performance performance) {
        Optional<Performance> performanceData = repository.findById(id);
        if (performanceData.isPresent()) {
            Performance updatedPerformance = performanceData.get();
            updatedPerformance.setTitle(performance.getDate());
            updatedPerformance.setDate(performance.getDate());
            updatedPerformance.setDescription(performance.getDescription());
            return new ResponseEntity<>(repository.save(updatedPerformance), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/performances")
    public ResponseEntity<HttpStatus> deleteAllPerformances() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/performances/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/performances/{date}")
    public ResponseEntity<List<Performance>> findPerformancesByDate(@PathVariable("date") String date) {
        List<Performance> performances = repository.findPerformancesByDate(date);
        if (performances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(performances, HttpStatus.OK);
        }
    }
}
