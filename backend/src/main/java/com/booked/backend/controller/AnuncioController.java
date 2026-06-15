package com.booked.backend.controller;

import com.booked.backend.entity.Anuncio;
import com.booked.backend.entity.Livro;
import com.booked.backend.entity.Usuario;
import com.booked.backend.repository.AnuncioRepository;
import com.booked.backend.repository.LivroRepository;
import com.booked.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioRepository repository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/carregar-anuncios")
    public List<Anuncio> carregarAnuncios() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Anuncio buscarAnuncio(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/publicar")
    public Anuncio publicarAnuncio(@RequestBody Anuncio anuncio) {

        // Buscar usuário e livro no banco
        Usuario usuario = usuarioRepository.findById(anuncio.getUsuario().getId()).orElseThrow();
        Livro livro = livroRepository.findById(anuncio.getLivro().getId()).orElseThrow();

        // Configurar o anúncio
        anuncio.setUsuario(usuario);
        anuncio.setLivro(livro);
        anuncio.setData_hora(LocalDateTime.now());

        // Registrar no banco de dados
        return repository.save(anuncio);

    }

}
