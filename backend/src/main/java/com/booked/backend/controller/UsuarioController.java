package com.booked.backend.controller;

import com.booked.backend.entity.UsuarioPerfilDTO;
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
    public Usuario verificarLogin(@RequestBody Usuario credenciais) {

        Usuario usuario = usuarioRepository.findByEmailAndSenha(
                credenciais.getEmail(),
                credenciais.getSenha()
        );

        return usuario;

    }

    @GetMapping("/existe/{email}")
    public Boolean existeUsuario(@PathVariable String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null) {
            return false;
        }
        return true;

    }

    @GetMapping("/buscar/{email}")
    public Integer buscarIdUsuario(@PathVariable String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario.getId();

    }

    @GetMapping("/buscar/id/{id}")
    public UsuarioPerfilDTO verPerfil(@PathVariable Integer id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UsuarioPerfilDTO(usuario.getEmail(), usuario.getGenero(), usuario.getCompras(), usuario.getTrocas());
    }

    @GetMapping("/buscar-usuario/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
    }

}
