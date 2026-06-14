package com.booked.backend.repository;

import org.springframework.data.jpa.repository.*;
import com.booked.backend.entity.SuspensaoEbook;
import java.util.List;

public interface SuspensaoEbookRepository extends JpaRepository<SuspensaoEbook, Integer> {

    List<SuspensaoEbook> findByEbook_IdEbook(Integer idEbook);

    List<SuspensaoEbook> findByModerador_IdModerador(Integer idModerador);
}