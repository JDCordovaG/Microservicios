package com.BuildMyPC.msvc_user_service.Services;

import com.BuildMyPC.msvc_user_service.Exceptions.UserException;
import com.BuildMyPC.msvc_user_service.Models.Dtos.UserDTO;
import com.BuildMyPC.msvc_user_service.Models.User;
import com.BuildMyPC.msvc_user_service.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User crearUser(UserDTO dto) {
        log.info("Iniciando creación de User con Email: {}", dto.getEmail());
        User user = new User();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
        user.setRolFuncional(dto.getRolFuncional());
        user.setEstado(dto.getEstado());
        user.setFechaRegistro(dto.getFechaRegistro());
        user.setEstado("ACTIVO");

        User guardada = repository.save(user);
        log.info("User guardado exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<User> listarTodas() {
        return repository.findAll();
    }

    public User buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserException("User no encontrada con ID: " + id));
    }


    public User desactivarUser(Long id) {
        log.info("Intentando desactivar User con ID: {}", id);
        User user = buscarPorId(id);
        user.setEstado("INACTIVO");
        User actualizada = repository.save(user);
        log.info("User desactivado exitosamente");
        return actualizada;
    }

}
