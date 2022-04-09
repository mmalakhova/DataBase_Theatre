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
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rank")
    private String rank;

    @Column(name = "prizes")
    private String prizes;

    @Column(name = "height")
    private Integer height;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    @Column(name = "vocals")
    private String vocals;

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
    }, mappedBy = "actors")
    @ToString.Exclude
    @JsonIgnore
    private Set<Performance> performances = new HashSet<>();

}
