package ru.nsu.fit.bdcourse.theatredemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "complexion_type")
    private String complexionType; //TODO: How to work with enums in http requests
    @Column(name = "vocal_ability")
    private String vocalAbility;
//    @ManyToMany(fetch = FetchType.LAZY,
//        cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//        }, mappedBy = "actors")
//    @JsonIgnore
//    private Set<Performance> performances = new HashSet<>();


    public Actor(String name, Integer age, String complexionType, String vocalAbility) {
        this.name = name;
        this.age = age;
        this.complexionType = complexionType;
        this.vocalAbility = vocalAbility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id && Objects.equals(name, actor.name) && Objects.equals(age, actor.age) && Objects.equals(complexionType, actor.complexionType) && Objects.equals(vocalAbility, actor.vocalAbility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, complexionType, vocalAbility);
    }
}
