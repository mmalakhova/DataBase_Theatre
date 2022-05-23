package ru.nsu.fit.bdcourse.theatredemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.nsu.fit.bdcourse.theatredemo.enums.GenderType;
import ru.nsu.fit.bdcourse.theatredemo.enums.StageDirectorType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "stage_directors")
public class StageDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_stage_director")
    private String stageDirectorType;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;


    @ManyToMany(mappedBy = "stageDirectors")
    @JsonIgnore
    @ToString.Exclude
    private Set<Performance> performances = new HashSet<>();

}
