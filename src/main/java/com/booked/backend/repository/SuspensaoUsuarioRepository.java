package com.booked.backend.repository;

import org.springframework.data.jpa.repository.*;
import com.booked.backend.entity.SuspensaoUsuario;
import java.util.List;

public interface SuspensaoUsuarioRepository extends JpaRepository<SuspensaoUsuario, Integer> {

    List<SuspensaoUsuario> findByUsuario_IdUsuario(Integer idUsuario);
    List<SuspensaoUsuario> findByModerador_IdModerador(Integer idModerador);
}