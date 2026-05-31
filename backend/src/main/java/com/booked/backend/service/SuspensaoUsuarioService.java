package com.booked.backend.service;

import org.springframework.stereotype.Service;
import com.booked.backend.entity.SuspensaoUsuario;
import com.booked.backend.repository.SuspensaoUsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SuspensaoUsuarioService {

    private final SuspensaoUsuarioRepository suspensaoUsuarioRepository;

    public SuspensaoUsuarioService(SuspensaoUsuarioRepository suspensaoUsuarioRepository) {
        this.suspensaoUsuarioRepository = suspensaoUsuarioRepository;
    }

    public List<SuspensaoUsuario> listarTodas() {
        return suspensaoUsuarioRepository.findAll();
    }

    public List<SuspensaoUsuario> listarPorUsuario(Integer idUsuario) {
        return suspensaoUsuarioRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public SuspensaoUsuario suspenderUsuario(SuspensaoUsuario suspensao) {
        if (suspensao.getMotivo() == null || suspensao.getMotivo().trim().isEmpty()) {
            throw new RuntimeException("O motivo da suspensão é obrigatório.");
        }
        if (suspensao.getModerador() == null) {
            throw new RuntimeException("Apenas moderadores podem suspender usuários.");
        }
        suspensao.setDataHora(LocalDateTime.now());
        return suspensaoUsuarioRepository.save(suspensao);
    }
}