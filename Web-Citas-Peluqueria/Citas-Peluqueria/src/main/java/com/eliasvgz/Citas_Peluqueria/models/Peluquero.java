package com.eliasvgz.Citas_Peluqueria.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "peluquero")
public class Peluquero {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    public Peluquero() {
    }

    @JsonCreator
    public Peluquero(@JsonProperty("nombre") String nombre) {
        this.nombre = nombre;
    }
}
