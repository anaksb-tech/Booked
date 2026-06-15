package com.booked.backend.controller;

import com.booked.backend.entity.Livro;
import com.booked.backend.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping("/registrar")
    public Livro registrarLivro(@RequestBody Livro livro) {

        return livroRepository.save(livro);

    }

    @GetMapping("/buscar/id/{id}")
    public Livro buscarLivro(@PathVariable Integer id) {

        return livroRepository.findById(id).orElseThrow();

    }

}
