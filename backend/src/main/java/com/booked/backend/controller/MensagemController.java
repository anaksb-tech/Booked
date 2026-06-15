package com.booked.backend.controller;

import com.booked.backend.entity.Mensagem;
import com.booked.backend.service.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping("/mensagens")
    public ResponseEntity<?> enviar(@RequestBody Mensagem mensagem) {
        try {
            return ResponseEntity.ok(mensagemService.enviarMensagem(mensagem));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/mensagens/conversa/{idUsuario1}/{idUsuario2}")
    public List<Mensagem> conversa(@PathVariable String idUsuario1, @PathVariable String idUsuario2) {
        return mensagemService.buscarConversa(idUsuario1, idUsuario2);
    }
}