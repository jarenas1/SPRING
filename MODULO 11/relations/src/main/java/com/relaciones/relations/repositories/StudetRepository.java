package com.relaciones.relations.repositories;

import com.relaciones.relations.entities.StundentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudetRepository extends JpaRepository<StundentEntity, Long> {

//    @Query("SELECT s from studentEntity s left join fetch s.courses where id=?1")
//    Optional<StundentEntity> findOneWithCourses(Long id);
}
