package com.grupo_4.msvc_construccion.services;

import com.grupo_4.msvc_construccion.dtos.ConstruccionDTO;
import com.grupo_4.msvc_construccion.exceptions.ConstruccionException;
import com.grupo_4.msvc_construccion.models.ConstruccionModel;
import com.grupo_4.msvc_construccion.repositories.ConstruccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConstruccionService {

    private static final Logger log = LoggerFactory.getLogger(ConstruccionService.class);
    private final ConstruccionRepository repository;

    public ConstruccionService(ConstruccionRepository repository) {
        this.repository = repository;
    }

    public ConstruccionModel crearConstruccion(ConstruccionDTO dto) {
        log.info("Creando nueva build para el usuario ID: {}", dto.getUsuarioId());

        ConstruccionModel cons = new ConstruccionModel();
        cons.setUsuarioId(dto.getUsuarioId());
        cons.setCpuId(dto.getCpuId());
        cons.setGpuId(dto.getGpuId());
        cons.setMotherboardId(dto.getMotherboardId());
        cons.setRamId(dto.getRamId());
        cons.setFuenteId(dto.getFuenteId());

        cons.setEstado("BORRADOR");
        cons.setFechaCreacion(LocalDate.now());
        cons.setFechaActualizacion(LocalDate.now());

        return repository.save(cons);
    }

    public List<ConstruccionModel> listarTodas() {
        return repository.findAll();
    }

    public ConstruccionModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConstruccionException("Build no encontrada con ID: " + id));
    }

    public void eliminarConstruccionBorrador(Long id) {
        ConstruccionModel cons = buscarPorId(id);
        if (!"BORRADOR".equalsIgnoreCase(cons.getEstado())) {
            throw new ConstruccionException("Solo se pueden eliminar builds en estado BORRADOR");
        }
        log.info("Eliminando build en borrador ID: {}", id);
        repository.delete(cons);
    }
}
