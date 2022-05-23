package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Musician;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceDto;
import ru.nsu.fit.bdcourse.theatredemo.service.MusicianService;
import ru.nsu.fit.bdcourse.theatredemo.service.PerformanceService;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class MusicianController {

    private MusicianService musicianService;

    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @GetMapping("/musicians")
    public Page<Musician> getAllMusicians(Integer pageNo, Integer pageSize, String sortBy,
                                             @RequestParam(name = "name") String name,
                                             @RequestParam(name = "gender") String gender,
                                             @RequestParam(name = "age") String age,
                                             @RequestParam(name = "instrument") String instrument) {
        return musicianService.getAllMusicians(pageNo, pageSize, sortBy, name, gender, age, instrument);
    }

    @GetMapping("/musicians/{id}")
    public ResponseEntity<Musician> getMusicianById(@PathVariable("id") Long id) {
        return musicianService.getMusicianById(id);
    }

    @PostMapping("/musicians")
    public ResponseEntity<Musician> createPerformance(@RequestBody Musician musician) {
        return musicianService.createNewMusician(musician);
    }

    @RequestMapping(value = "/musicians", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Musician> updateMusician(@RequestBody Musician musician) {
        return musicianService.updateMusician(musician);
    }


    @DeleteMapping("/musicians/{id}")
    public ResponseEntity<HttpStatus> deleteMusicianById(@PathVariable("id") Long id) {
        return musicianService.deleteMusicianById(id);
    }
}
