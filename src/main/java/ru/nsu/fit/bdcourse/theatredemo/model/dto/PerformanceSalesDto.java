package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PerformanceSalesDto {

    private String performanceName;

    private Integer salesSum;

    public PerformanceSalesDto(String performanceName, Integer salesSum) {
        this.performanceName = performanceName;
        this.salesSum = salesSum;
    }

}
