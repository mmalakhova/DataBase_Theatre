package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceCreateRequest;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceDto;

import ru.nsu.fit.bdcourse.theatredemo.service.PerformanceService;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performances")
    public Page<PerformanceDto> getAllPerformances(Integer pageNo, Integer pageSize, String sortBy,
                                                   @RequestParam(name = "name") String name,
                                                   @RequestParam(name = "genre") String genre,
                                                   @RequestParam(name = "age_rating") String ageRating,
                                                   @RequestParam(name = "date") String date) {
        return performanceService.getAllPerformances(pageNo, pageSize, sortBy, name, genre, ageRating, date);
    }

    @GetMapping("/performances/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable("id") Long id) {
        return performanceService.getPerformanceById(id);
    }

    @PostMapping("/performances")
    public ResponseEntity<Performance> createPerformance(@RequestBody Performance performance) {
        return performanceService.createNewPerformance(performance);
    }

    @RequestMapping(value = "/performances", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Performance> updatePerformance(@RequestBody Performance performance) {
        return performanceService.updatePerformance(performance);
    }


    @DeleteMapping("/performances/{id}")
    public ResponseEntity<HttpStatus> deletePerformanceById(@PathVariable("id") Long id) {
        return performanceService.deletePerformanceById(id);
    }

}
