package com.booked.backend.controller;

import com.booked.backend.entity.Pesquisa;
import com.booked.backend.service.PesquisaService;
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
public class PesquisaController {

    private final PesquisaService pesquisaService;

    public PesquisaController(PesquisaService pesquisaService) {
        this.pesquisaService = pesquisaService;
    }

    @PostMapping("/pesquisas")
    public ResponseEntity<?> registrar(@RequestBody Pesquisa pesquisa) {
        try {
            return ResponseEntity.ok(pesquisaService.registrarPesquisa(pesquisa));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pesquisas/historico/{idUsuario}")
    public List<Pesquisa> historico(@PathVariable Integer idUsuario) {
        return pesquisaService.historicoPorUsuario(idUsuario);
    }
}