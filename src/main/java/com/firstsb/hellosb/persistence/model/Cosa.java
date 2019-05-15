package com.firstsb.hellosb.persistence.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cosa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column (nullable = false)
    private String descripcion;

    public Cosa(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cosa)) return false;
        Cosa cosa = (Cosa) o;
        return getId() == cosa.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
