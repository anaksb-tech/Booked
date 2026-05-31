package com.booked.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booked.backend.entity.Denuncia;
import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    List<Denuncia> findByModeradorIsNull();

    List<Denuncia> findByAnuncio_IdAnuncio(Integer idAnuncio);

    List<Denuncia> findByEbook_IdEbook(Integer idEbook);
}