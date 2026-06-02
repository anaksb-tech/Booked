package com.booked.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.booked.backend.entity.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Integer> {


    List<Oferta> findByAnuncio_IdAnuncio(Integer idAnuncio);
    List<Oferta> findByUsuario_IdUsuario(Integer idUsuario);
}