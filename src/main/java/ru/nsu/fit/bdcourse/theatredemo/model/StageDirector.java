package ru.nsu.fit.bdcourse.theatredemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stage_directors")
public class StageDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Type(type = "stage_director_type")
    @Column(name = "type_of_stage_director")
    private String stageDirectorType;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Type(type = "gender_type")
    @Column(name = "gender")
    private String gender;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    }, mappedBy = "stage_directors")
    @JsonIgnore
    @ToString.Exclude
    private Set<Performance> performances = new HashSet<>();

}
