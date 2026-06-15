package com.booked.backend.controller;

import com.booked.backend.entity.UsuarioPerfilDTO;
import com.booked.backend.repository.UsuarioRepository;
import com.booked.backend.entity.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    // Construtor fazendo a injeção de dependência (substitui o @Autowired)
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar-usuario")
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public Usuario verificarLogin(@RequestBody Usuario credenciais) {

        Usuario usuario = usuarioRepository.findByEmailAndSenha(
                credenciais.getEmail(),
                credenciais.getSenha()
        );

        if(usuario == null) {
            return null; // Retorna vazio, o que faz seu JS exibir o alerta de erro
        }

        return usuario;
    }

    @GetMapping("/buscar/id/{id}")
    public UsuarioPerfilDTO verPerfil(@PathVariable Integer id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UsuarioPerfilDTO(usuario.getEmail(), usuario.getGenero());
    }

    @GetMapping("/buscar-usuario/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {

        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }
}