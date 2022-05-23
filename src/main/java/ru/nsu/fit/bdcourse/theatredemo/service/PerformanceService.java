package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceCreateRequest;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceDto;
import ru.nsu.fit.bdcourse.theatredemo.repository.PerformanceRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public Page<PerformanceDto> getAllPerformances(Integer pageNo, Integer pageSize, String sortBy,
                                                   String name, String genre, String ageRating,
                                                   String date) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        ExampleMatcher matcher = ExampleMatcher
//                .matchingAll()
//                .withMatcher("name", contains().ignoreCase())
//                .withMatcher("genre", contains().ignoreCase())
//                .withMatcher("date", contains().ignoreCase())
//                .withMatcher("age_rating", contains().ignoreCase());

//        Performance performanceToFind = Performance
//                .builder()
//                .name(name)
//                .genre(genre)
//                .ageRating(ageRating)
//                .date(date.equals("") ? null : LocalDate.parse(date))
//                .build();
        return performanceRepository.findAll(/*Example.of(performanceToFind, matcher)*/ paging).map(PerformanceDto::new);
    }

    public ResponseEntity<Performance> getPerformanceById(Long id) {
        Optional<Performance> performanceData = performanceRepository.findById(id);
        return performanceData.map(t ->
                new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Performance> createNewPerformance(Performance performance) {
//        System.out.println(performance);
//        Author author = performanceRepository.findAuthorByName(performance.getAuthor());
//        ConcertTour concertTour = performanceRepository.findConcertTourByName(performance.getConcertTour());
//        LocalDate date = performance.getDate().equals("") ? null : LocalDate.parse(performance.getDate());
//        Performance newPerformance = new Performance(performance.getId(), performance.getName(), author, concertTour, performance.getGenre(),
//                performance.getAgeRating(), date, performance.getDescr());
        return new ResponseEntity<>(performanceRepository.save(performance), HttpStatus.CREATED);
    }

    public ResponseEntity<Performance> updatePerformance(Performance performance) {
        Optional<Performance> performanceData = performanceRepository.findById(performance.getId());
        if (performanceData.isPresent()) {
            Performance updatedPerformance = performanceData.get();
            if (performance.getName() != null) {
                updatedPerformance.setName(performance.getName());
            }
            if (performance.getGenre() != null) {
                updatedPerformance.setGenre(performance.getGenre());
            }
            if (performance.getAgeRating() != null) {
                updatedPerformance.setAgeRating(performance.getAgeRating());
            }
            if (performance.getDate() != null) {
                updatedPerformance.setDate(performance.getDate());
            }
            if (performance.getDescr() != null) {
                updatedPerformance.setDescr(performance.getDescr());
            }
            if (performance.getAuthor() != null) {
                updatedPerformance.setAuthor(performance.getAuthor());
            }
            if (performance.getConcertTour() != null) {
                updatedPerformance.setConcertTour(performance.getConcertTour());
            }
            return new ResponseEntity<>(performanceRepository.save(updatedPerformance), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deletePerformanceById(Long id) {
        Optional<Performance> performanceToDelete = performanceRepository.findById(id);
        if (performanceToDelete.isPresent()) {
            performanceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
