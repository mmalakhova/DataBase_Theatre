package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.bdcourse.theatredemo.model.Actor;
import ru.nsu.fit.bdcourse.theatredemo.repository.ActorRepository;

import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Page<Actor> getAllActors(Integer pageNo, Integer pageSize, String sortBy,
                                   String name, String height, String age, String vocals,
                                   String gender) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        ExampleMatcher matcher = ExampleMatcher
//                .matchingAll()
//                .withMatcher("name", contains().ignoreCase())
//                .withMatcher("country", contains().ignoreCase())
//                .withMatcher("gender", contains().ignoreCase());
//        Author authorToFind = Author
//                .builder()
//                .name(name)
//                .country(country)
//                .gender(gender)
//                .build();
        return actorRepository.findAll(/*Example.of(actorToFind, matcher),*/ paging);
    }


    public ResponseEntity<Actor> getActorById(Long id) {
        Optional<Actor> actorData = actorRepository.findById(id);
        return actorData.map(actor ->
                new ResponseEntity<>(actor, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Actor> createNewActor(Actor actor) {
        return new ResponseEntity<>(actorRepository.save(actor), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Actor> updateActor(Actor actor) {
        Optional<Actor> actorData = actorRepository.findById(actor.getId());
        if (actorData.isPresent()) {
            Actor updatedActor = actorData.get();
            if (actor.getName() != null) {
                updatedActor.setName(actor.getName());
            }
            if (actor.getHeight() != null) {
                updatedActor.setHeight(actor.getHeight());
            }
            if (actor.getGender() != null) {
                updatedActor.setGender(actor.getGender());
            }
            if (actor.getVocals() != null) {
                updatedActor.setVocals(actor.getVocals());
            }
            if (actor.getAge() != null) {
                updatedActor.setAge(actor.getAge());
            }
            return new ResponseEntity<>(actorRepository.save(updatedActor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteActorById(Long id) {
        Optional<Actor> actorToDelete = actorRepository.findById(id);
        if (actorToDelete.isPresent()) {
            actorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
