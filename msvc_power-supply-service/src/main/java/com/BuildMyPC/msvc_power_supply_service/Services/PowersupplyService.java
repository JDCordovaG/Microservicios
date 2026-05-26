package com.BuildMyPC.msvc_power_supply_service.Services;

import com.BuildMyPC.msvc_power_supply_service.Exceptions.PowersupplyException;
import com.BuildMyPC.msvc_power_supply_service.Models.Dtos.PowersupplyDTO;
import com.BuildMyPC.msvc_power_supply_service.Models.Powersupply;
import com.BuildMyPC.msvc_power_supply_service.Repositories.PowersupplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowersupplyService {

    private static final Logger log = LoggerFactory.getLogger(PowersupplyRepository.class);
    private final PowersupplyRepository repository;

    public PowersupplyService(PowersupplyRepository repository) {
        this.repository = repository;
    }

    public Powersupply crearFuentePoder(PowersupplyDTO dto) {
        log.info("Creando Fuente de Poder de {} Watts", dto.getPotenciaWatts());

        Powersupply fuente = new Powersupply();
        fuente.setComponenteId(dto.getComponenteId());
        fuente.setPotenciaWatts(dto.getPotenciaWatts());
        fuente.setCertificacion(dto.getCertificacion());
        fuente.setFormato(dto.getFormato());
        fuente.setEsModular(dto.getEsModular());
        fuente.setEstado("ACTIVO");

        return repository.save(fuente);
    }

    public List<Powersupply> listarTodas() {
        return repository.findAll();
    }

    public Powersupply buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PowersupplyException("Fuente de poder no encontrada con ID: " + id));
    }

    public Powersupply desactivarFuentePoder(Long id) {
        log.info("Desactivando Fuente de Poder ID: {}", id);
        Powersupply fuente = buscarPorId(id);
        fuente.setEstado("INACTIVO");
        return repository.save(fuente);
    }
}