package ru.nsu.fit.bdcourse.theatredemo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.Actor;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.ActorsRolesVocalsDto;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query(nativeQuery = true, value = "select actors.name, actors.vocals, " +
            "roles.name, roles.vocal_req from actors cross join roles")
    Page<ActorsRolesVocalsDto> crossJoinVocalsQuery(Pageable pageable);
}
