package com.jpa.JPA.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //INDICAMOS COMO SE GENERA EL ID
    private Long id;

    private String name;

    private String lastname;

    @Column(name = "programming_language", nullable = false) //modificamos su nombre y que es obligatorio
    private  String programmingLanguage;
    @Embedded
    //LLAMAMOS LA AUDITORIA
    private Audit audit = new Audit();


    public PersonEntity() {
    }

    public PersonEntity(Long id, String lastname, String name, String programmingLanguage) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.programmingLanguage = programmingLanguage;
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

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;


    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonEntity person = (PersonEntity) object;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(lastname, person.lastname) && Objects.equals(programmingLanguage, person.programmingLanguage) && Objects.equals(audit, person.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, programmingLanguage, audit);
    }
}
