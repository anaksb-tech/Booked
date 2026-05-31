package com.booked.backend.service;

import org.springframework.stereotype.Service;
import com.booked.backend.entity.Compra;
import com.booked.backend.repository.CompraRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> listarPorUsuario(Integer id_usuario) {
        return compraRepository.findByUsuario_IdUsuario(id_usuario);
    }

    public Compra realizarCompra(Compra compra) {
        Compra compraExistente = compraRepository
                .findByUsuario_IdUsuarioAndEbook_IdEbook(
                        compra.getUsuario().getId(),
                        compra.getEbook().getId_ebook()
                );

        if (compraExistente != null) {
            throw new RuntimeException("Usuário já possui este ebook.");
        }

        compra.setDataHora(LocalDateTime.now());
        return compraRepository.save(compra);
    }

    public boolean reembolsoAutomatico(Integer id_compra) {
        Compra compra = compraRepository.findById(id_compra)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));

        LocalDateTime limiteReembolso = compra.getDataHora().plusDays(7);
        return LocalDateTime.now().isBefore(limiteReembolso);
    }

    public void reembolsar(Integer id_compra) {
        if (!compraRepository.existsById(id_compra)) {
            throw new RuntimeException("Compra não encontrada.");
        }
        compraRepository.deleteById(id_compra);
    }
}