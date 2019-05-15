package com.firstsb.hellosb.persistence.repository;

import com.firstsb.hellosb.persistence.model.Cosa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CosaRepository extends CrudRepository<Cosa, Long> {
    List<Cosa> findByNombre(String nombre);
}