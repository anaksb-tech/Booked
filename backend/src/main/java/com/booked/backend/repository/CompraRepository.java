package com.booked.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booked.backend.entity.Compra;
import java.util.*;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findByUsuario_IdUsuario(Integer idUsuario);

    Compra findByUsuario_IdUsuarioAndEbook_IdEbook(Integer idUsuario, Integer idEbook);
}