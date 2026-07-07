package com.BuildMyPC.msvc_ram_service.Services;

import com.BuildMyPC.msvc_ram_service.Exceptions.RamException;
import com.BuildMyPC.msvc_ram_service.Models.Dtos.RamDTO;
import com.BuildMyPC.msvc_ram_service.Models.Ram;
import com.BuildMyPC.msvc_ram_service.Repositories.RamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RamService {

    private static final Logger log = LoggerFactory.getLogger(RamService.class);
    private final RamRepository repository;

    public RamService(RamRepository repository) {
        this.repository = repository;
    }


    public Ram crearRam(RamDTO dto) {
        log.info("Iniciando creación de ram con tipo ddr: {}", dto.getTipoDdr());
        Ram ram = new Ram();
        ram.setComponenteId(dto.getComponenteId());
        ram.setTipoDdr(dto.getTipoDdr());
        ram.setCapacidadGb(dto.getCapacidadGb());
        ram.setFrecuenciaMhz(dto.getFrecuenciaMhz());
        ram.setLatenciaCl(dto.getLatenciaCl());
        ram.setModulos(dto.getModulos());
        ram.setVoltaje(dto.getVoltaje());

        Ram guardada = repository.save(ram);
        log.info("CPU guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Ram> listarTodas() {
        return repository.findAll();
    }

    public Ram buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RamException("CPU no encontrada con ID: " + id));
    }


}
