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

    private String status = "disponivel";

    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    //Getters

    public Integer getId_anuncio() {
        return id_anuncio;
    }


    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public String getStatus() {
        return status;
    }


    public String getFoto() {
        return foto;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public Livro getLivro() {
        return livro;
    }

    // Setters
    public void setId_anuncio(Integer id_anuncio) {
        this.id_anuncio = id_anuncio;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
