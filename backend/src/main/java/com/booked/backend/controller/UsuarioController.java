package com.booked.backend.controller;

import com.booked.backend.repository.UsuarioRepository;
import com.booked.backend.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastrar-usuario")
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @PostMapping("/login")
    public Integer verificarLogin(@RequestBody Usuario credenciais) {

        Usuario usuario = usuarioRepository.findByEmailAndSenha(
                credenciais.getEmail(),
                credenciais.getSenha()
        );

        if(usuario == null) {
            return null;
        }

        return usuario.getId();

    }

}
