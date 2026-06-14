package com.booked.backend.entity;

public class EbookDTO {

    // Atributos
    private int id;

    private String titulo;

    // Construtor
    public EbookDTO(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

}