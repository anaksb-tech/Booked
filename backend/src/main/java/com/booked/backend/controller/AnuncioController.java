package com.booked.backend.controller;

import com.booked.backend.entity.Anuncio;
import com.booked.backend.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioRepository repository;

    @GetMapping("/carregar-anuncios")
    public List<Anuncio> carregarAnuncios() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Anuncio buscarAnuncio(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

}
