package com.booked.backend.entity;

public class UsuarioPerfilDTO {

    // Atributos
    private String email;

    private String genero;

    private String compras;
    // Construtor
    public UsuarioPerfilDTO(String email, String genero,String compras) {
        this.email = email;
        this.genero = genero;
        this.compras = compras;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getGenero() { return genero; }

    public String getCompras() { return compras; }
}
