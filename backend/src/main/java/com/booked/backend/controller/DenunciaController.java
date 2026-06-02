package com.booked.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booked.backend.entity.Denuncia;
import com.booked.backend.service.DenunciaService;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping("/denuncias")
    public List<Denuncia> listarTodas() {
        return denunciaService.listarTodas();
    }

    /*
    @GetMapping("/anuncio/{id_anuncio}")
    public List<Denuncia> listarPorAnuncio(@PathVariable Integer id_anuncio) {
        return denunciaService.listarPorAnuncio(id_anuncio);
    }

    @GetMapping("/ebook/{id_ebook}")
    public List<Denuncia> listarPorEbook(@PathVariable Integer id_ebook) {
        return denunciaService.listarPorEbook(id_ebook);
    }
    */

    @PostMapping("/denuncias")
    public ResponseEntity<?> registrar(@RequestBody Denuncia denuncia) {
        try {
            return ResponseEntity.ok(denunciaService.registrarDenuncia(denuncia));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}