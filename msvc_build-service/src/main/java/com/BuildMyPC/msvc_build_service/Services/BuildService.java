package com.BuildMyPC.msvc_build_service.Services;

import com.BuildMyPC.msvc_build_service.Clients.CompatibilityClient;
import com.BuildMyPC.msvc_build_service.Clients.UserClient;
import com.BuildMyPC.msvc_build_service.Exceptions.BuildException;
import com.BuildMyPC.msvc_build_service.Models.Build;
import com.BuildMyPC.msvc_build_service.Models.Dtos.BuildDTO;
import com.BuildMyPC.msvc_build_service.Repositories.BuildRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class BuildService {

    private static final Logger log = LoggerFactory.getLogger(BuildService.class);
    private final BuildRepository repository;
    private final UserClient userClient;
    private final CompatibilityClient compatibilityClient;

    public BuildService(BuildRepository repository, UserClient userClient, CompatibilityClient compatibilityClient) {
        this.repository = repository;
        this.userClient = userClient;
        this.compatibilityClient = compatibilityClient;
    }


    public Build crearBuild(BuildDTO dto) {
        log.info("Iniciando creación de Build con id de usuario: {}", dto.getUsuarioId());

        try {
            userClient.obtenerUsuario(dto.getUsuarioId());
        } catch (Exception e) {
            throw new BuildException("El usuario no existe o el user-service no está disponible");
        }
        Build build = new Build();
        build.setUsuarioId(dto.getUsuarioId());
        build.setCpuId(dto.getCpuId());
        build.setGpuId(dto.getGpuId());
        build.setMotherboardId(dto.getMotherboardId());
        build.setRamId(dto.getRamId());
        build.setFuenteId(dto.getFuenteId());
        build.setFechaCreacion(dto.getFechaCreacion());
        build.setFechaActualizacion(dto.getFechaActualizacion());
        build.setEstado("ACTIVO");

        Build guardada = repository.save(build);

        try {
            Map<String, Object> resultado = compatibilityClient.validarBuild(Map.of(
                    "buildId", guardada.getId(),
                    "cpuId", guardada.getCpuId(),
                    "gpuId", guardada.getGpuId(),
                    "motherboardId", guardada.getMotherboardId(),
                    "ramId", guardada.getRamId(),
                    "fuenteId", guardada.getFuenteId()
            ));
            log.info("Resultado de compatibilidad: {}", resultado);
        } catch (Exception e) {
            log.error("Error al validar compatibilidad: {}", e.getMessage());
        }
        log.info("Build guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Build> listarTodas() {
        return repository.findAll();
    }

    public Build buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BuildException("Build no encontrada con ID: " + id));
    }


    public Build desactivarBuild(Long id) {
        log.info("Intentando desactivar Build con ID: {}", id);
        Build build = buscarPorId(id);
        build.setEstado("INACTIVO");
        Build actualizada = repository.save(build);
        log.info("Build desactivada exitosamente");
        return actualizada;
    }

}
