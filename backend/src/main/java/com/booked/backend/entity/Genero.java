package com.booked.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Genero {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genero;
    private String tipo;

    // Getters
    public Integer getId() {
        return id_genero;
    }

    public String getTipo() {
        return tipo;
    }

    //Setters
    public void setId(Integer id_genero) {
        this.id_genero = id_genero;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}