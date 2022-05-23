package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.StageDirector;
import ru.nsu.fit.bdcourse.theatredemo.service.StageDirectorService;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class StageDirectorController {
    private StageDirectorService stageDirectorService;

    public StageDirectorController(StageDirectorService stageDirectorService) {
        this.stageDirectorService = stageDirectorService;
    }

    @GetMapping("/stagedirectors")
    public Page<StageDirector> getAllStageDirectors(Integer pageNo, Integer pageSize, String sortBy,
                                                    @RequestParam(name = "name") String name,
                                                    @RequestParam(name = "type") String type,
                                                    @RequestParam(name = "age") String age,
                                                    @RequestParam(name = "gender") String gender) {
        return stageDirectorService.getAllStageDirectors(pageNo, pageSize, sortBy, name, type, age, gender);
    }


    @GetMapping("/stagedirectors/{id}")
    public ResponseEntity<StageDirector> getStageDirectorById(@PathVariable("id") Long id) {
        return stageDirectorService.getStageDirectorById(id);
    }

    @PostMapping("/stagedirectors")
    public ResponseEntity<StageDirector> createStageDirector(@RequestBody StageDirector stageDirector) {
        return stageDirectorService.createNewStageDirector(stageDirector);
    }

    @RequestMapping(value = "/stagedirectors", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<StageDirector> updateStageDirector(@RequestBody StageDirector stageDirector) {
        return stageDirectorService.updateStageDirector(stageDirector);
    }


    @DeleteMapping("/stagedirectors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable("id") Long id) {
        return stageDirectorService.deleteStageDirectorById(id);
    }
}
