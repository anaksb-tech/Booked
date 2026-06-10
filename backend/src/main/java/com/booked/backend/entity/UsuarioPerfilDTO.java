package com.booked.backend.entity;

public class UsuarioPerfilDTO {

    // Atributos
    private String email;

    private String genero;

    // Construtor
    public UsuarioPerfilDTO(String email, String genero) {
        this.email = email;
        this.genero = genero;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return genero;
    }

}
