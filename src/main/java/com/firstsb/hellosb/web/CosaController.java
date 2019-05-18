package com.firstsb.hellosb.web;

import com.firstsb.hellosb.persistence.model.Cosa;
import com.firstsb.hellosb.persistence.repository.CosaRepository;
import com.firstsb.hellosb.web.Exception.CosaIdMismatchException;
import com.firstsb.hellosb.web.Exception.CosaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cosas")
public class CosaController {

    @Autowired
    private CosaRepository cosaRepository;

    @GetMapping
    public Iterable findAll() {
        return cosaRepository.findAll();
    }

    @GetMapping("/nombre/{cosaNombre}")
    public List findByNombre(@PathVariable String cosaNombre) {
        return cosaRepository.findByNombre(cosaNombre);
    }

    @GetMapping("/{id}")
    public Cosa findOne(@PathVariable Long id) {
        return cosaRepository.findById(id)
                .orElseThrow(CosaNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cosa create(@RequestBody Cosa cosa) {
        return cosaRepository.save(cosa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cosaRepository.findById(id)
                .orElseThrow(CosaNotFoundException::new);
        cosaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Cosa updateCosa(@RequestBody Cosa cosa, @PathVariable Long id) {
        if (cosa.getId() != id) {
            throw new CosaIdMismatchException();
        }
        cosaRepository.findById(id)
                .orElseThrow(CosaNotFoundException::new);
        return cosaRepository.save(cosa);
    }
}
