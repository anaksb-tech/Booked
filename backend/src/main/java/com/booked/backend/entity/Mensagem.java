package com.booked.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mensagem {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String rev;

    private String tipo;

    @JsonProperty("id_usuario1")
    private String idUsuario1;

    @JsonProperty("id_usuario2")
    private String idUsuario2;

    private String texto;

    private String imagem;

    @JsonProperty("data_hora")
    private String dataHora;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRev() { return rev; }
    public void setRev(String rev) { this.rev = rev; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getIdUsuario1() { return idUsuario1; }
    public void setIdUsuario1(String idUsuario1) { this.idUsuario1 = idUsuario1; }

    public String getIdUsuario2() { return idUsuario2; }
    public void setIdUsuario2(String idUsuario2) { this.idUsuario2 = idUsuario2; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}