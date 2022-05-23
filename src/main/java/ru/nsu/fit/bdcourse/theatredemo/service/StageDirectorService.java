package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.fit.bdcourse.theatredemo.model.Author;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.StageDirector;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.TicketDto;
import ru.nsu.fit.bdcourse.theatredemo.repository.StageDirectorRepository;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class StageDirectorService {
    private final StageDirectorRepository stageDirectorRepository;

    public StageDirectorService(StageDirectorRepository stageDirectorRepository) {
        this.stageDirectorRepository = stageDirectorRepository;
    }

    public Page<StageDirector> getAllStageDirectors(Integer pageNo, Integer pageSize, String sortBy,
                                         String name, String typeOfStageDirector, String age, String gender) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        ExampleMatcher matcher = ExampleMatcher
//                .matchingAll()
//                .withMatcher("name", contains().ignoreCase())
//                .withMatcher("typeOfStageDirector", contains().ignoreCase())
//                .withMatcher("age", contains().ignoreCase())
//                .withMatcher("gender", contains().ignoreCase());
//        StageDirector stageDirectorToFind = StageDirector
//                .builder()
//                .name(name)
//                .age(age.equals("") ? null : Integer.parseInt(age))
//                .stageDirectorType(typeOfStageDirector)
//                .gender(gender)
//                .build();
        return stageDirectorRepository.findAll(/*Example.of(stageDirectorToFind, matcher),*/ paging);
    }


    public ResponseEntity<StageDirector> getStageDirectorById(Long id) {
        Optional<StageDirector> stageDirectorData = stageDirectorRepository.findById(id);
        return stageDirectorData.map(sd ->
                new ResponseEntity<>(sd, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<StageDirector> createNewStageDirector(StageDirector stageDirector) {
        return new ResponseEntity<>(stageDirectorRepository.save(stageDirector), HttpStatus.CREATED);
    }

    public ResponseEntity<StageDirector> updateStageDirector(StageDirector stageDirector) {
        Optional<StageDirector> sdData = stageDirectorRepository.findById(stageDirector.getId());
        if (sdData.isPresent()) {
            StageDirector updatedStageDirector = sdData.get();
            if (stageDirector.getName() != null) {
                updatedStageDirector.setName(stageDirector.getName());
            }
            if (stageDirector.getAge() != null) {
                updatedStageDirector.setAge(stageDirector.getAge());
            }
            if (stageDirector.getGender() != null) {
                updatedStageDirector.setGender(stageDirector.getGender());
            }
            if (stageDirector.getStageDirectorType() != null) {
                updatedStageDirector.setStageDirectorType(stageDirector.getStageDirectorType());
            }
            return new ResponseEntity<>(stageDirectorRepository.save(updatedStageDirector), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteStageDirectorById(Long id) {
        Optional<StageDirector> stageDirectorToDelete = stageDirectorRepository.findById(id);
        if (stageDirectorToDelete.isPresent()) {
            stageDirectorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
