package ru.nsu.fit.bdcourse.theatredemo.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "jsonb")
    @Column(name = "data", columnDefinition = "jsonb")
    private NewsData data;
}
