package com.booked.backend.repository;

import com.booked.backend.entity.Anuncio;
import com.booked.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    List<Anuncio> findByUsuario(Usuario usuario);
}