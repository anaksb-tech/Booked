package com.booked.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booked.backend.entity.SuspensaoEbook;
import com.booked.backend.service.SuspensaoEbookService;
import java.util.List;

@RestController
@RequestMapping("/suspensoes/ebooks")
@CrossOrigin(origins = "*")
public class SuspensaoEbookController {

    private final SuspensaoEbookService suspensaoEbookService;

    public SuspensaoEbookController(SuspensaoEbookService suspensaoEbookService) {
        this.suspensaoEbookService = suspensaoEbookService;
    }

    @GetMapping
    public List<SuspensaoEbook> listarTodas() {
        return suspensaoEbookService.listarTodas();
    }

    @GetMapping("/{idEbook}")
    public List<SuspensaoEbook> listarPorEbook(@PathVariable Integer idEbook) {
        return suspensaoEbookService.listarPorEbook(idEbook);
    }

    @PostMapping
    public ResponseEntity<?> suspender(@RequestBody SuspensaoEbook suspensao) {
        try {
            return ResponseEntity.ok(suspensaoEbookService.suspenderEbook(suspensao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}