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
@Table(name = "stage_directors")
@TypeDef(
        name = "gender_type",
        typeClass = GenderType.class
)
@TypeDef(
        name = "stage_director_type",
        typeClass = StageDirectorType.class
)
public class StageDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Type(type = "stage_director_type")
    @Column(name = "type_of_stage_director")
    private StageDirectorType stageDirectorType;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Type(type = "gender_type")
    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(mappedBy = "stageDirectors")
    @JsonIgnore
    @ToString.Exclude
    private Set<Performance> performances = new HashSet<>();

}
