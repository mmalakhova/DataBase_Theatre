package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActorsRolesVocalsDto {

    private String actorName;
    private String atctorVocals;
    private String roleName;
    private String roleVocals;

    public ActorsRolesVocalsDto(String roleName, String roleVocals,
                                String actorName, String actorVocals) {
        this.actorName = actorName;
        this.atctorVocals = actorVocals;
        this.roleName = roleName;
        this.roleVocals = roleVocals;
    }
}
