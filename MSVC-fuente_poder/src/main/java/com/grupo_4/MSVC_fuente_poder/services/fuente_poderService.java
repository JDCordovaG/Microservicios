package com.grupo_4.MSVC_fuente_poder.services;

import com.grupo_4.MSVC_fuente_poder.dtos.fuente_poderDTO;
import com.grupo_4.MSVC_fuente_poder.exceptions.fuente_poderException;
import com.grupo_4.MSVC_fuente_poder.models.fuente_poderModel;
import com.grupo_4.MSVC_fuente_poder.repositories.fuente_poderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class fuente_poderService {

    private static final Logger log = LoggerFactory.getLogger(fuente_poderRepository.class);
    private final fuente_poderRepository repository;

    public fuente_poderService(fuente_poderRepository repository) {
        this.repository = repository;
    }

    public fuente_poderModel crearFuentePoder(fuente_poderDTO dto) {
        log.info("Creando Fuente de Poder de {} Watts", dto.getPotenciaWatts());

        fuente_poderModel fuente = new fuente_poderModel();
        fuente.setComponenteId(dto.getComponenteId());
        fuente.setPotenciaWatts(dto.getPotenciaWatts());
        fuente.setCertificacion(dto.getCertificacion());
        fuente.setFormato(dto.getFormato());
        fuente.setEsModular(dto.getEsModular());
        fuente.setEstado("ACTIVO");

        return repository.save(fuente);
    }

    public List<fuente_poderModel> listarTodas() {
        return repository.findAll();
    }

    public fuente_poderModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new fuente_poderException("Fuente de poder no encontrada con ID: " + id));
    }

    public fuente_poderModel desactivarFuentePoder(Long id) {
        log.info("Desactivando Fuente de Poder ID: {}", id);
        fuente_poderModel fuente = buscarPorId(id);
        fuente.setEstado("INACTIVO");
        return repository.save(fuente);
    }
}
