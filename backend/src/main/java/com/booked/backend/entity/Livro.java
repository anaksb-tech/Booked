package com.booked.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Livro {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Integer idLivro;
    private String titulo;
    private String autor;
    @Column(columnDefinition = "TEXT")
    private String descricao;

    // Aqui cria a relação usando a tabela do SQL
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genero_livro",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Genero> generos;

    // Getters
    public Integer getId() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    //Setters
    public void setId(Integer id_livro) {
        this.idLivro = id_livro;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}