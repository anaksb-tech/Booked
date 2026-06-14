package com.booked.backend.entity;

public class UsuarioPerfilDTO {

    // Atributos
    private String email;

    private String genero;

    private String compras;

    private String trocas;
    // Construtor
    public UsuarioPerfilDTO(String email, String genero, String compras, String trocas) {
        this.email = email;
        this.genero = genero;
        this.compras = compras;
        this.trocas = trocas;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getGenero() { return genero; }

    public String getCompras() { return compras; }

    public String getTrocas() { return trocas; }
}
