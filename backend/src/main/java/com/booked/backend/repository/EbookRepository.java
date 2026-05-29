package com.booked.backend.repository;

import com.booked.backend.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, Integer> {
}
