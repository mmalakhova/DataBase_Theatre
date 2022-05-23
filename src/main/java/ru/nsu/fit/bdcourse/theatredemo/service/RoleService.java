package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.stereotype.Service;
import ru.nsu.fit.bdcourse.theatredemo.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
