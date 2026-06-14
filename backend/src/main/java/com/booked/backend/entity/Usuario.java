package com.booked.backend.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nome;

    private String email;

    private String genero;

    private String senha;

    private Integer ranque = 0;

    private String compras;

    // Getters
    public Integer getId() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return genero;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getRanque() {
        return ranque;
    }

    public String getCompras() {
        return compras;
    }

    // Setters
    public void setId(Integer id_usuario) {
        this.idUsuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setRanque(Integer ranque) {
        this.ranque = ranque;
    }

    public void setCompras(String compras) { this.compras = compras; }

}
