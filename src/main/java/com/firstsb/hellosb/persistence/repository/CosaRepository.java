package com.firstsb.hellosb.persistence.repository;

import com.firstsb.hellosb.persistence.model.Cosa;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface CosaRepository extends MongoRepository<Cosa, String> {
    List<Cosa> findByNombre(String nombre);
    List<Cosa> findByDescripcion(String descripcion);
}