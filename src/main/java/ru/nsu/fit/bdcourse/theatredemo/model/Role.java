package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.nsu.fit.bdcourse.theatredemo.enums.GenderType;
import ru.nsu.fit.bdcourse.theatredemo.enums.RoleType;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@TypeDef(
        name = "gender_type",
        typeClass = GenderType.class
)
@TypeDef(
        name = "role_type",
        typeClass = RoleType.class
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    @ToString.Exclude
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    @ToString.Exclude
    private Actor actor;

    @Column(name = "vocal_requirements")
    private String vocalRequirements;

    @Column(name = "height_requirements")
    private Integer heightRequirements;

    @Column(name = "age_requirements")
    private Integer ageRequirements;

    @Type(type = "gender_type")
    @Column(name = "gender_requirements")
    private GenderType genderRequirements;

    @Type(type = "role_type")
    @Column(name = "first_or_second_role")
    private RoleType roleType;
}
