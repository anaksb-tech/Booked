package com.booked.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
public class SuspensaoEbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suspensao_ebook")
    private Integer idSuspensaoEbook;

    @ManyToOne
    @JoinColumn(name = "id_ebook", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ebook ebook;

    @ManyToOne
    @JoinColumn(name = "id_moderador", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Moderador moderador;

    @Column(name = "motivo", nullable = false, length = 100)
    private String motivo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    // Getters
    public Integer getIdSuspensaoEbook() { return idSuspensaoEbook; }
    public Ebook getEbook() { return ebook; }
    public Moderador getModerador() { return moderador; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getDataHora() { return dataHora; }

    // Setters
    public void setIdSuspensaoEbook(Integer idSuspensaoEbook) { this.idSuspensaoEbook = idSuspensaoEbook; }
    public void setEbook(Ebook ebook) { this.ebook = ebook; }
    public void setModerador(Moderador moderador) { this.moderador = moderador; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}