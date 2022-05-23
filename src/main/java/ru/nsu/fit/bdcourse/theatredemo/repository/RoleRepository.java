package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.Role;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.RoleActorNameDto;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "select roles.name, actors.name from roles " +
            "left outer join actors on roles.actor_id = actors.id")
    Page<RoleActorNameDto> roleAndActorsOuterJoinQuery(Pageable pageable);
}
