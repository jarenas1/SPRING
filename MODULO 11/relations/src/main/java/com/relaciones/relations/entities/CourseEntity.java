package com.relaciones.relations.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String instructor;

    //AÑADIMOS LA LISTA DE CURSOS PARA QUE SEA BIDIRECCIONAL
    @ManyToMany
    Set<StundentEntity> students = new HashSet<>(); //USAMOS SET PARA HACER MAS AVANZADAS LAS CONSULTAS JPQL

    public CourseEntity() {
    }

    public CourseEntity(String instructor, String name) {
        this.instructor = instructor;
        this.name = name;
    }

    public Set<StundentEntity> getClients() {
        return students;
    }

    public void setClients(Set<StundentEntity> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CourseEntity that = (CourseEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(instructor, that.instructor) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructor, students);
    }
}


