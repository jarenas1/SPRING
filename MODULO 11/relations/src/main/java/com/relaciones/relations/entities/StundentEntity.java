package com.relaciones.relations.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class StundentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<CourseEntity> courses = new ArrayList<>(); //INICIALIZAMOS

    public StundentEntity() {
    }

    public StundentEntity(List<CourseEntity> courses, String lastname, String name) {
        this.courses = courses;
        this.lastname = lastname;
        this.name = name;
    }

    public StundentEntity(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StundentEntity{" +
                "courses=" + courses +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
