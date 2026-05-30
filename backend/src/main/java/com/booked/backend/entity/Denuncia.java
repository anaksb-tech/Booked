package com.booked.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDenuncia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moderador", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Moderador moderador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Anuncio anuncio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ebook", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ebook ebook;

    private String motivo;
    private LocalDateTime dataHora;

    // Getters
    public Integer getIdDenuncia() {
        return idDenuncia;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Moderador getModerador() {
        return moderador;
    }
    public Anuncio getAnuncio() {
        return anuncio;
    }
    public Ebook getEbook() {
        return ebook;
    }
    public String getMotivo() {
        return motivo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Setters
    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    public void setEbook(Ebook ebook) {
        this.ebook = ebook;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}