package com.booked.backend.entity;

import jakarta.persistence.*;

@Entity
public class Moderador {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moderador")
    private Integer idModerador;

    private String nome;

    private String email;

    private String senha;

    private String genero;

    // Getters

    // Setters

}
