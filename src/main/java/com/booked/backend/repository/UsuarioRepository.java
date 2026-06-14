package com.booked.backend.repository;

import com.booked.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);

}
