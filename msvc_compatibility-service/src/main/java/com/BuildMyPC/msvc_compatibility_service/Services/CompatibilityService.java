package com.BuildMyPC.msvc_compatibility_service.Services;

import com.BuildMyPC.msvc_compatibility_service.Exceptions.CompatibilityException;
import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.CompatibilityDTO;
import com.BuildMyPC.msvc_compatibility_service.Repositories.CompatibilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityService {

    private static final Logger log = LoggerFactory.getLogger(CompatibilityService.class);
    private final CompatibilityRepository repository;

    public CompatibilityService(CompatibilityRepository repository) {
        this.repository = repository;
    }


    public ValidacionCompatibility crearCompatibility(CompatibilityDTO dto) {
        log.info("Iniciando creación de Compatibility con buildid: {}", dto.getBuildId());
        ValidacionCompatibility compatibility = new ValidacionCompatibility();
        compatibility.setBuildId(dto.getBuildId());
        compatibility.setCompatible(dto.getCompatible());
        compatibility.setConsumoEstimadoWatts(dto.getConsumoEstimadoWatts());
        compatibility.setMargenFuente(dto.getMargenFuente());
        compatibility.setObservaciones(dto.getObservaciones());
        compatibility.setFechaValidacion(dto.getFechaValidacion());

        ValidacionCompatibility guardada = repository.save(compatibility);
        log.info("Compatibility guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<ValidacionCompatibility> listarTodas() {
        return repository.findAll();
    }

    public ValidacionCompatibility buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CompatibilityException("Compatibility no encontrada con ID: " + id));
    }

}
