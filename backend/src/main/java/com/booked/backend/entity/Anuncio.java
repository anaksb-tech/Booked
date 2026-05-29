package com.booked.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Anuncio {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_anuncio;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private LocalDateTime data_hora;

    private String foto;

    @ManyToOne
    @JoinTable(name = "id_usuario")
    private Usuario usuario;

    private Livro livro;

}
