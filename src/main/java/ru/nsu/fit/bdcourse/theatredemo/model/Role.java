package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    @ToString.Exclude
    private Performance performance;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column(name = "vocal_requirements")
    private String vocalRequirements;

    @Column(name = "height_requirements")
    private Integer heightRequirements;

    @Column(name = "age_requirements")
    private Integer ageRequirements;

    @Enumerated(EnumType.STRING)
    @Type(type = "gender_type")
    @Column(name = "gender_requirements")
    private String genderRequirements;

    @Enumerated(EnumType.STRING)
    @Type(type = "role_type")
    @Column(name = "first_or_second_role")
    private String roleType;
}
