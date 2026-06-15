package com.booked.backend.controller;

import com.booked.backend.entity.Compra;
import com.booked.backend.repository.CompraRepository;
import com.booked.backend.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/usuario/{id_usuario}")
    public List<Compra> listarPorUsuario(@PathVariable Integer id_usuario) {
        return compraService.listarPorUsuario(id_usuario);
    }

    @PostMapping
    public ResponseEntity<?> comprar(@RequestBody Compra compra) {
        try {
            return ResponseEntity.ok(compraService.realizarCompra(compra));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id_compra}/reembolso-elegivel")
    public ResponseEntity<?> verificarReembolso(@PathVariable Integer id_compra) {
        try {
            boolean elegivel = compraService.reembolsoAutomatico(id_compra);
            return ResponseEntity.ok(elegivel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id_compra}/reembolsar")
    public ResponseEntity<?> reembolsar(@PathVariable Integer id_compra) {
        try {
            compraService.reembolsar(id_compra);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/comprar")
    public Compra publicar(@RequestBody Compra compra) {

        return compraService.realizarCompra(compra);

    }

}