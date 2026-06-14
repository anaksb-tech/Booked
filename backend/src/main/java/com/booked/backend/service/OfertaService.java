package com.booked.backend.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.booked.backend.entity.Oferta;
import com.booked.backend.entity.Anuncio;
import com.booked.backend.repository.OfertaRepository;
import com.booked.backend.repository.AnuncioRepository;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final AnuncioRepository anuncioRepository;

    public OfertaService(OfertaRepository ofertaRepository, AnuncioRepository anuncioRepository) {
        this.ofertaRepository = ofertaRepository;
        this.anuncioRepository = anuncioRepository;
    }

    public List<Oferta> listarTodas() {
        return ofertaRepository.findAll();
    }

    public List<Oferta> listarPorAnuncio(Integer idAnuncio) {
        return ofertaRepository.findByAnuncio_IdAnuncio(idAnuncio);
    }

    public List<Oferta> listarPorUsuario(Integer idUsuario) {
        return ofertaRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public Oferta criarOferta(Oferta oferta) {
        oferta.setDataHora(LocalDateTime.now());
        oferta.setStatus("pendente");
        return ofertaRepository.save(oferta);
    }

    public Oferta aceitarOferta(Integer idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));

        oferta.setStatus("aceita");
        ofertaRepository.save(oferta);

        Anuncio anuncio = oferta.getAnuncio();
        anuncio.setStatus("fechado");
        anuncioRepository.save(anuncio);

        List<Oferta> outras = ofertaRepository.findByAnuncio_IdAnuncio(anuncio.getId_anuncio());
        for (Oferta outra : outras) {
            if (!outra.getIdOferta().equals(idOferta)) {
                outra.setStatus("rejeitada");
                ofertaRepository.save(outra);
            }
        }

        return oferta;
    }

    public Oferta rejeitarOferta(Integer idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));
        oferta.setStatus("rejeitada");
        return ofertaRepository.save(oferta);
    }

    public void deletarOferta(Integer idOferta) {
        if (!ofertaRepository.existsById(idOferta)) {
            throw new RuntimeException("Oferta não encontrada");
        }
        ofertaRepository.deleteById(idOferta);
    }
}