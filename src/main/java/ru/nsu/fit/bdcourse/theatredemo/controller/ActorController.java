package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Actor;
import ru.nsu.fit.bdcourse.theatredemo.service.ActorService;


@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class ActorController {
    private final ActorService actorService;

    private ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public Page<Actor> getAllActor(Integer pageNo, Integer pageSize, String sortBy,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "height") String height,
                                   @RequestParam(name = "age") String age,
                                   @RequestParam(name = "vocals") String vocals,
                                   @RequestParam(name = "gender") String gender) {
        return actorService.getAllActors(pageNo, pageSize, sortBy, name, height, age, vocals, gender);
    }


    @GetMapping("/actors/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Long id) {
        return actorService.getActorById(id);
    }

    @PostMapping("/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return actorService.createNewActor(actor);
    }

    @RequestMapping(value = "/actor", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor) {
        return actorService.updateActor(actor);
    }


    @DeleteMapping("/actor/{id}")
    public ResponseEntity<HttpStatus> deleteActorById(@PathVariable("id") Long id) {
        return actorService.deleteActorById(id);
    }
}
