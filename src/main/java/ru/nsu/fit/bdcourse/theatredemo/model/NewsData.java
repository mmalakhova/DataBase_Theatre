package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsData implements Serializable {
    private static final long serialVersionUID = 7702L;

    private String date;
    private String title;
    private String desc;
    private String imageUrl;
}
