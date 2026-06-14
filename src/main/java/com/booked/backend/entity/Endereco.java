package com.booked.backend.entity;

import jakarta.persistence.*;

@Entity
public class Endereco {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    private String cidade;

    private String bairro;

    private String rua;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // Getters

    // Setters

}
