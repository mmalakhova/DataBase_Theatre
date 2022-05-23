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

    @Column(name = "name")
    private String name;

    @Column(name = "vocal_req")
    private String vocalRequirements;

    @Column(name = "height_req")
    private Integer heightRequirements;

    @Column(name = "age_req")
    private Integer ageRequirements;

    @Column(name = "gender_req")
    private String genderRequirements;

    @Column(name = "first_or_second_role")
    private String roleType;
}
