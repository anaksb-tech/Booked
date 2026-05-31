package com.booked.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Genero {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer idGenero;
    private String tipo;

    // Getters
    public Integer getId() {
        return idGenero;
    }

    public String getTipo() {
        return tipo;
    }

    //Setters
    public void setId(Integer id_genero) {
        this.idGenero = id_genero;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}