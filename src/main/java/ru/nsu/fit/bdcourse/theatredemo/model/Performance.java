package ru.nsu.fit.bdcourse.theatredemo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "performances_actors",
            joinColumns = @JoinColumn(name = "performance_id", referencedColumnName = "actors_id"))
    private List<Actor> actors = new ArrayList<>();

    public Performance(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Performance that = (Performance) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
