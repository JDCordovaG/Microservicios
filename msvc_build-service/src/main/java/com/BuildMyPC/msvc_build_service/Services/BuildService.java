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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

        // Se guarda inicialmente como BORRADOR (vía @PrePersist)
        Build guardada = repository.save(build);

        // Flujo integrador: validar compatibilidad
        try {
            Map<String, Object> resultado = compatibilityClient.validarBuild(Map.of(
                    "buildId", guardada.getId(),
                    "cpuId", guardada.getCpuId(),
                    "gpuId", guardada.getGpuId(),
                    "motherboardId", guardada.getMotherboardId(),
                    "ramId", guardada.getRamId(),
                    "fuenteId", guardada.getFuenteId()
            ));

            // Evaluamos la respuesta (Asumiendo que el cliente devuelve una llave "compatible" booleana)
            boolean esCompatible = (boolean) resultado.getOrDefault("compatible", false);
            guardada.setEstado(esCompatible ? "VALIDADA" : "INCOMPATIBLE");
            guardada = repository.save(guardada);

            log.info("Resultado de compatibilidad procesado. Nuevo estado: {}", guardada.getEstado());
        } catch (Exception e) {
            log.error("Error al validar compatibilidad: {}. Se mantiene en BORRADOR.", e.getMessage());
        }

        return guardada;
    }

    @Transactional(readOnly = true)
    public List<Build> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Build buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BuildException("Build no encontrada con ID: " + id));
    }

    @Transactional
    public Build actualizarBuild(Long id, BuildDTO dto) {
        Build build = buscarPorId(id);

        // Regla implícita: si se cambian componentes, debe volver a evaluarse.
        build.setCpuId(dto.getCpuId());
        build.setGpuId(dto.getGpuId());
        build.setMotherboardId(dto.getMotherboardId());
        build.setRamId(dto.getRamId());
        build.setFuenteId(dto.getFuenteId());
        build.setEstado("BORRADOR"); // Al modificarse, pierde su validación anterior.

        return repository.save(build);
    }

    @Transactional
    public void eliminarBuildBorrador(Long id) {
        log.info("Intentando eliminar Build con ID: {}", id);
        Build build = buscarPorId(id);

        // Validación estricta del caso semestral
        if (!"BORRADOR".equals(build.getEstado())) {
            throw new BuildException("Solo se pueden eliminar o archivar builds en estado BORRADOR.");
        }

        repository.delete(build);
        log.info("Build eliminada exitosamente");
    }
}