package com.BuildMyPC.msvc_auth_service.Services;

import com.BuildMyPC.msvc_auth_service.Exceptions.AuthException;
import com.BuildMyPC.msvc_auth_service.Models.Auth;
import com.BuildMyPC.msvc_auth_service.Models.Dtos.AuthDTO;
import com.BuildMyPC.msvc_auth_service.Repositories.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final AuthRepository repository;

    public AuthService(AuthRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Auth crearAuth(AuthDTO dto) {
        log.info("Iniciando creación de Auth con email: {}", dto.getEmail());

        // Validación de correo duplicado según requerimiento del caso
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            log.error("Fallo al crear: El email {} ya existe", dto.getEmail());
            throw new AuthException("Ya existe una cuenta asociada a este correo.");
        }

        Auth auth = new Auth();
        auth.setEmail(dto.getEmail());
        // NOTA: Para cumplir el caso al 100%, aquí deberías inyectar PasswordEncoder y usar:
        // auth.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        auth.setPasswordHash(dto.getPasswordHash());
        auth.setRol(dto.getRol());

        Auth guardada = repository.save(auth);
        log.info("Auth guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    @Transactional(readOnly = true)
    public List<Auth> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Auth buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthException("Auth no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public Auth buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Auth no encontrada con email: " + email));
    }

    @Transactional
    public Auth actualizarAuth(Long id, AuthDTO dto) {
        log.info("Actualizando Auth con ID: {}", id);
        Auth auth = buscarPorId(id);

        // Solo actualiza si el email nuevo no le pertenece a otro usuario
        if (!auth.getEmail().equals(dto.getEmail()) && repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AuthException("El nuevo correo ya está en uso por otra cuenta.");
        }

        auth.setEmail(dto.getEmail());
        auth.setPasswordHash(dto.getPasswordHash());
        auth.setRol(dto.getRol());

        return repository.save(auth);
    }

    @Transactional
    public Auth desactivarAuth(Long id) {
        log.info("Intentando desactivar Auth con ID: {}", id);
        Auth auth = buscarPorId(id);
        auth.setEstado("INACTIVO");
        Auth actualizada = repository.save(auth);
        log.info("Auth desactivado exitosamente");
        return actualizada;
    }
}