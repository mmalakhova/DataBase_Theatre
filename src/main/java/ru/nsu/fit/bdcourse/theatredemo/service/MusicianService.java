package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.fit.bdcourse.theatredemo.model.Musician;
import ru.nsu.fit.bdcourse.theatredemo.repository.MusicianRepository;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class MusicianService {
    private final MusicianRepository musicianRepository;

    public MusicianService(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    public Page<Musician> getAllMusicians(Integer pageNo, Integer pageSize, String sortBy,
                                        String name, String gender, String age, String instrument) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("gender", contains().ignoreCase())
                .withMatcher("age", contains().ignoreCase())
                .withMatcher("instrument", contains().ignoreCase());
        Musician musicianToFind = Musician
                .builder()
                .name(name)
                .gender(gender)
                .instrument(instrument)
                .age(age.equals("") ? null : Integer.parseInt(age))
                .build();
        return musicianRepository.findAll(Example.of(musicianToFind, matcher), paging);
    }

    public ResponseEntity<Musician> getMusicianById(Long id) {
        Optional<Musician> musicianData = musicianRepository.findById(id);
        return musicianData.map(t ->
                new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Musician> createNewMusician(Musician musician) {
        return new ResponseEntity<>(musicianRepository.save(musician), HttpStatus.CREATED);
    }

    public ResponseEntity<Musician> updateMusician(Musician musician) {
        Optional<Musician> musicianData = musicianRepository.findById(musician.getId());
        if (musicianData.isPresent()) {
            Musician updateMusician = musicianData.get();
            if (musician.getName() != null) {
                updateMusician.setName(musician.getName());
            }
            if (musician.getGender() != null) {
                updateMusician.setGender(musician.getGender());
            }
            if (musician.getAge() != null) {
                updateMusician.setAge(musician.getAge());
            }
            if (musician.getInstrument() != null) {
                updateMusician.setInstrument(musician.getInstrument());
            }
            return new ResponseEntity<>(musicianRepository.save(updateMusician), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteMusicianById(Long id) {
        Optional<Musician> musicianToDelete = musicianRepository.findById(id);
        if (musicianToDelete.isPresent()) {
            musicianRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
