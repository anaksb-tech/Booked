package com.booked.backend.repository;

import com.booked.backend.entity.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeradorRepository extends JpaRepository<Moderador, Integer> {
}
