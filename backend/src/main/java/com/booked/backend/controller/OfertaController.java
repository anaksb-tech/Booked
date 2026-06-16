package com.booked.backend.controller;

import com.booked.backend.entity.Oferta;
import com.booked.backend.service.OfertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/oferta")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping("/ofertas")
    public List<Oferta> listarTodas() {
        return ofertaService.listarTodas();
    }

    @GetMapping("/anuncio/{idAnuncio}")
    public List<Oferta> listarPorAnuncio(@PathVariable Integer idAnuncio) {
        return ofertaService.listarPorAnuncio(idAnuncio);
    }

    @GetMapping("/ofertas-usuario/{id}")
    public List<Oferta> listarPorUsuario(@PathVariable Integer id) {
        return ofertaService.listarPorUsuario(id);
    }

    @PostMapping("/registrar")
    public Oferta criar(@RequestBody Oferta oferta) {
        return ofertaService.criarOferta(oferta);
    }

    @PutMapping("/ofertas/{id}/aceitar")
    public ResponseEntity<?> aceitar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ofertaService.aceitarOferta(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ofertas/{id}/rejeitar")
    public ResponseEntity<?> rejeitar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ofertaService.rejeitarOferta(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/ofertas/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            ofertaService.deletarOferta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}