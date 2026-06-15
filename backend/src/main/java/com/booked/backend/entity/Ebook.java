package com.booked.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ebook {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ebook")
    private Integer idEbook;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    private String autor;

    private BigDecimal preco;

    private LocalDateTime data_hora;

    @ManyToMany
    @JoinTable(
            name = "genero_ebook",
            joinColumns = @JoinColumn(name = "id_ebook"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private List<Genero> generos;

    // Getters
    public Integer getId_ebook() {
        return idEbook;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Fazer resto depois...


}
