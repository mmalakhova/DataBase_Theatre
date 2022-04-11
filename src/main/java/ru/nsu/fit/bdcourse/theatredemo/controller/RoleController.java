package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Role;
import ru.nsu.fit.bdcourse.theatredemo.repository.PerformanceRepository;
import ru.nsu.fit.bdcourse.theatredemo.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theatre")
public class RoleController {

    private final PerformanceRepository performanceRepository;
    private final RoleRepository roleRepository;

    public RoleController(PerformanceRepository performanceRepository,
                          RoleRepository roleRepository) {
        this.performanceRepository = performanceRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = new ArrayList<>(roleRepository.findAll());
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id")Long id) {
        Optional<Role> roleData = roleRepository.findById(id);
        return roleData.map(role ->
                new ResponseEntity<>(role, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return null;
    }
}
