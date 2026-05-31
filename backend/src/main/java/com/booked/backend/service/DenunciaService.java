package com.booked.backend.service;

import org.springframework.stereotype.Service;
import com.booked.backend.entity.Denuncia;
import com.booked.backend.repository.DenunciaRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }

    public List<Denuncia> listarPendentes() {
        return denunciaRepository.findByModeradorIsNull();
    }

    public List<Denuncia> listarPorAnuncio(Integer idAnuncio) {
        return denunciaRepository.findByAnuncio_IdAnuncio(idAnuncio);
    }

    public List<Denuncia> listarPorEbook(Integer idEbook) {
        return denunciaRepository.findByEbook_IdEbook(idEbook);
    }
    public Denuncia registrarDenuncia(Denuncia denuncia) {
        boolean temAnuncio = denuncia.getAnuncio() != null;
        boolean temEbook = denuncia.getEbook() != null;

        if (temAnuncio == temEbook) {
            throw new RuntimeException("A denúncia deve ser sobre um anúncio ou sobre um ebook, não ambos.");
        }

        denuncia.setDataHora(LocalDateTime.now());
        denuncia.setModerador(null);

        return denunciaRepository.save(denuncia);
    }

    public void deletarDenuncia(Integer idDenuncia) {
        if (!denunciaRepository.existsById(idDenuncia)) {
            throw new RuntimeException("Denúncia não encontrada");
        }
        denunciaRepository.deleteById(idDenuncia);
    }
}