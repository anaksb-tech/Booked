package com.booked.backend.controller;
import com.booked.backend.repository.GeneroRepository;
import com.booked.backend.entity.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class GeneroController {

    @Autowired
    private GeneroRepository repository;

    @GetMapping("/listar-generos")
    public List<Genero> listarTodos() {
        return repository.findAll();
    }
}