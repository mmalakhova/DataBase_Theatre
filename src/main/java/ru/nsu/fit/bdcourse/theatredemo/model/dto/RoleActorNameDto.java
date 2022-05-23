package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleActorNameDto {

    private String roleName;

    private String actorName;

    public RoleActorNameDto(String actorName, String roleName) {
        this.actorName = actorName;
        this.roleName = roleName;
    }
}
