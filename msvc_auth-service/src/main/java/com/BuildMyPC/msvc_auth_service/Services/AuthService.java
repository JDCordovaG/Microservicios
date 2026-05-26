package com.BuildMyPC.msvc_auth_service.Services;

import com.BuildMyPC.msvc_auth_service.Exceptions.AuthException;
import com.BuildMyPC.msvc_auth_service.Models.Auth;
import com.BuildMyPC.msvc_auth_service.Models.Dtos.AuthDTO;
import com.BuildMyPC.msvc_auth_service.Repositories.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final AuthRepository repository;

    public AuthService(AuthRepository repository) {
        this.repository = repository;
    }


    public Auth crearAuth(AuthDTO dto) {
        log.info("Iniciando creación de Auth con Id: {}", dto.getEmail());
        Auth auth = new Auth();
        auth.setEmail(dto.getEmail());
        auth.setPasswordHash(dto.getPasswordHash());
        auth.setRol(dto.getRol());
        auth.setEstado(dto.getEstado());
        auth.setFechaCreacion(dto.getFechaCreacion());
        auth.setUltimoLogin(dto.getUltimoLogin());
        auth.setEstado("ACTIVO");

        Auth guardada = repository.save(auth);
        log.info("Auth guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Auth> listarTodas() {
        return repository.findAll();
    }

    public Auth buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthException("Auth no encontrada con ID: " + id));
    }


    public Auth desactivarAuth(Long id) {
        log.info("Intentando desactivar Auth con ID: {}", id);
        Auth auth = buscarPorId(id);
        auth.setEstado("INACTIVO");
        Auth actualizada = repository.save(auth);
        log.info("Auth desactivado exitosamente");
        return actualizada;
    }

}
