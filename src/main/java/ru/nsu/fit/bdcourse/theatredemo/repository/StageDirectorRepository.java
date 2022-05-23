package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.StageDirector;

@Repository
public interface StageDirectorRepository extends JpaRepository<StageDirector, Long> {
    @Query("from StageDirector p where p.name = :searchText or p.stageDirectorType = :searchText or p.gender = :searchText or p.age = :searchText order by p.id")
    Page<StageDirector> findAllStageDirectors(@Param("searchText") String searchText, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from stage_directors where age between 18 and 55 order by stage_directors.name")
    Page<StageDirector> findMiddleAgedDStageDirectors(Pageable pageable);
}
