package com.booked.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
public class SuspensaoUsuario {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suspensao_usuario")
    private Integer idSuspensaoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_moderador", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Moderador moderador;

    @Column(name = "motivo", nullable = false, length = 100)
    private String motivo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    // Getters
    public Integer getIdSuspensaoUsuario() {
        return idSuspensaoUsuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Moderador getModerador() {
        return moderador;
    }
    public String getMotivo() {
        return motivo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Setters
    public void setIdSuspensaoUsuario(Integer idSuspensaoUsuario) {
        this.idSuspensaoUsuario = idSuspensaoUsuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}