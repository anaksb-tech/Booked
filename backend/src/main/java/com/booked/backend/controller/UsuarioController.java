package com.booked.backend.controller;

import com.booked.backend.repository.UsuarioRepository;
import com.booked.backend.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastrar-usuario")
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

}
