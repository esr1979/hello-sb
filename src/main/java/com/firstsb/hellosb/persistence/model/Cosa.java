package com.firstsb.hellosb.persistence.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cosa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String descripcion;

    public Cosa(){
        super();
    }

    public Cosa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    @Override
    public String toString() {
        return "Cosa [id=" + id + ", nombre=" + nombre + ", descripción=" + descripcion + "]";
    }

}
