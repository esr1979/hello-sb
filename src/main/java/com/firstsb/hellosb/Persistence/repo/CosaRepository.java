package com.firstsb.hellosb.Persistence.repo;

import com.firstsb.hellosb.Persistence.model.Cosa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CosaRepository extends CrudRepository<Cosa, Long> {
    List<Cosa> findByNombre(String nombre);
}