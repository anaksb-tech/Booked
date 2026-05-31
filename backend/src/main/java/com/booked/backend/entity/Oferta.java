package com.booked.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
public class Oferta {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Integer idOferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_anuncio", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Livro livro;

    private String foto;
    private LocalDateTime dataHora;
    private String status = "pendente";

    // Getters
    public Integer getIdOferta() {
        return idOferta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public Livro getLivro() {
        return livro;
    }

    public String getFoto() {
        return foto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }
    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}