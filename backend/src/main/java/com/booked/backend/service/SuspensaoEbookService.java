package com.booked.backend.service;

import org.springframework.stereotype.Service;
import com.booked.backend.entity.SuspensaoEbook;
import com.booked.backend.repository.SuspensaoEbookRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SuspensaoEbookService {

    private final SuspensaoEbookRepository suspensaoEbookRepository;

    public SuspensaoEbookService(SuspensaoEbookRepository suspensaoEbookRepository) {
        this.suspensaoEbookRepository = suspensaoEbookRepository;
    }

    public List<SuspensaoEbook> listarTodas() {
        return suspensaoEbookRepository.findAll();
    }

    public List<SuspensaoEbook> listarPorEbook(Integer idEbook) {
        return suspensaoEbookRepository.findByEbook_IdEbook(idEbook);
    }

    public SuspensaoEbook suspenderEbook(SuspensaoEbook suspensao) {
        if (suspensao.getMotivo() == null || suspensao.getMotivo().trim().isEmpty()) {
            throw new RuntimeException("O motivo da suspensão é obrigatório.");
        }

        if (suspensao.getModerador() == null) {
            throw new RuntimeException("Apenas moderadores podem suspender ebooks.");
        }

        suspensao.setDataHora(LocalDateTime.now());
        return suspensaoEbookRepository.save(suspensao);
    }
}