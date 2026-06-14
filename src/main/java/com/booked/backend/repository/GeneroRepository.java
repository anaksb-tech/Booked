package com.booked.backend.repository;

import com.booked.backend.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}