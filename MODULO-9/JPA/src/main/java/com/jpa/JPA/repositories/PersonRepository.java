package com.jpa.JPA.repositories;

import com.jpa.JPA.DTO.PersonDto;
import com.jpa.JPA.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//EXTENDEMOS DE JPA Y AÑADIMOS EN LOS GENERICOS EL TIPO DE ENTIDAD Y EL TIPO DEL ID}
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    //METODO PERSONALIZADO POR MEDIO DE NOMBRE
    List<PersonEntity> findByProgrammingLanguage(String programmingLanguage);

    List<PersonEntity> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    //METODO PERSONALIZADO CON JPQL
    @Query("SELECT p FROM PersonEntity p WHERE p.name = ?1 and p.lastname = ?2")
    List<PersonEntity> personByNameQuery (String name, String lastName);


    //OBTENIENDO DATOS ESPECIFICOS, NOMBRE Y LENGUAJE
     @Query("SELECT p.name, p.programmingLanguage FROM PersonEntity p")
    List<Object[]> dataUser (); //INDICAMOS QUE DEVOLVERA UNA LISTA DE ARRAYS DE TIPO OBJECT

    //QUERY PARA DEVOLVER EL DTO
    //SE CREA LA QUERY QUE LLENARA AUTOMATICAMENTE EL DTO
    @Query("SELECT new com.jpa.JPA.DTO.PersonDto(p.name, p.lastname) FROM PersonEntity p")
    List<PersonDto> findAllDTO();
}
