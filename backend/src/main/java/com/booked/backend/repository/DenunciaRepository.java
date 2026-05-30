package com.booked.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.booked.backend.entity.Denuncia;
import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    List<Denuncia> findByModeradorIsNull();

    @Query("SELECT d FROM Denuncia d WHERE d.anuncio.id_anuncio = :id_anuncio")
    List<Denuncia> buscarPorIdAnuncio(@Param("id_anuncio") Integer id_anuncio);

    @Query("SELECT d FROM Denuncia d WHERE d.ebook.id_ebook = :id_ebook")
    List<Denuncia> buscarPorIdEbook(@Param("id_ebook") Integer id_ebook);
}