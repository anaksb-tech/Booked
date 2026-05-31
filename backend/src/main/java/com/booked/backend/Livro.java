package com.booked.backend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.List;
import jakarta.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Livro {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_livro;
    private String titulo;
    private String autor;
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
        return id_livro;
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
        this.id_livro = id_livro;
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