package com.booked.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booked.backend.entity.SuspensaoUsuario;
import com.booked.backend.service.SuspensaoUsuarioService;
import java.util.List;

@RestController
@RequestMapping("/suspensoes/usuarios")
@CrossOrigin(origins = "*")
public class SuspensaoUsuarioController {

    private final SuspensaoUsuarioService suspensaoUsuarioService;

    public SuspensaoUsuarioController(SuspensaoUsuarioService suspensaoUsuarioService) {
        this.suspensaoUsuarioService = suspensaoUsuarioService;
    }

    @GetMapping
    public List<SuspensaoUsuario> listarTodas() {
        return suspensaoUsuarioService.listarTodas();
    }

    @GetMapping("/{idUsuario}")
    public List<SuspensaoUsuario> listarPorUsuario(@PathVariable Integer idUsuario) {
        return suspensaoUsuarioService.listarPorUsuario(idUsuario);
    }

    @PostMapping
    public ResponseEntity<?> suspender(@RequestBody SuspensaoUsuario suspensao) {
        try {
            return ResponseEntity.ok(suspensaoUsuarioService.suspenderUsuario(suspensao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}