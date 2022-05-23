package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceDto;
import ru.nsu.fit.bdcourse.theatredemo.repository.ConcertTourRepository;
import ru.nsu.fit.bdcourse.theatredemo.service.ConcertTourService;
import ru.nsu.fit.bdcourse.theatredemo.service.PerformanceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class ConcertTourController {

    private ConcertTourService concertTourService;

    public ConcertTourController(ConcertTourService concertTourService) {
        this.concertTourService = concertTourService;
    }

    @GetMapping("/concerttours")
    public Page<ConcertTour> getAllConcertTours(Integer pageNo, Integer pageSize, String sortBy,
                                                   @RequestParam(name = "title") String title,
                                                   @RequestParam(name = "descr") String descr,
                                                   @RequestParam(name = "start_date") String startDate,
                                                   @RequestParam(name = "end_date") String endDate) {
        return concertTourService.getAllConcertTours(pageNo, pageSize, sortBy, title, descr, startDate, endDate);
    }

    @GetMapping("/concerttours/{id}")
    public ResponseEntity<ConcertTour> getConcertTourById(@PathVariable("id") Long id) {
        return concertTourService.getConcertTourById(id);
    }

    @PostMapping("/concerttours")
    public ResponseEntity<ConcertTour> createConcertTour(@RequestBody ConcertTour concertTour) {
        return concertTourService.createNewConcertTour(concertTour);
    }

    @RequestMapping(value = "/concerttours", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<ConcertTour> updateConcertTour(@RequestBody ConcertTour concertTour) {
        return concertTourService.updateConcertTour(concertTour);
    }


    @DeleteMapping("/concerttours/{id}")
    public ResponseEntity<HttpStatus> deleteConcertTourById(@PathVariable("id") Long id) {
        return concertTourService.deleteConcertTourById(id);
    }
}
