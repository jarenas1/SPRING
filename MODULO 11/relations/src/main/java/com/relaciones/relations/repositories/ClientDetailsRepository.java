package com.relaciones.relations.repositories;

import com.relaciones.relations.entities.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
}
