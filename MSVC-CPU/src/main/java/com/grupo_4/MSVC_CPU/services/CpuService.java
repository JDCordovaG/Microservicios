package com.grupo_4.MSVC_CPU.services;

import com.grupo_4.MSVC_CPU.exceptions.CpuException;
import com.grupo_4.MSVC_CPU.exceptions.CpuExceptionHandler;
import com.grupo_4.MSVC_CPU.models.Cpu;
import com.grupo_4.MSVC_CPU.dtos.CpuDTO;
import com.grupo_4.MSVC_CPU.repositories.CpuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CpuService {

    private static final Logger log = LoggerFactory.getLogger(CpuService.class);
    private final CpuRepository repository;

    public CpuService(CpuRepository repository) {
        this.repository = repository;
    }


    public Cpu crearCpu(CpuDTO dto) {
        log.info("Iniciando creación de CPU con socket: {}", dto.getSocket());
        Cpu cpu = new Cpu();
        cpu.setComponenteId(dto.getComponenteId());
        cpu.setSocket(dto.getSocket());
        cpu.setNucleos(dto.getNucleos());
        cpu.setHilos(dto.getHilos());
        cpu.setFrecuenciaBase(dto.getFrecuenciaBase());
        cpu.setFrecuenciaTurbo(dto.getFrecuenciaTurbo());
        cpu.setTdpWatts(dto.getTdpWatts());
        cpu.setSoportaDdr4(dto.getSoportaDdr4());
        cpu.setSoportaDdr5(dto.getSoportaDdr5());
        cpu.setEstado("ACTIVO");

        Cpu guardada = repository.save(cpu);
        log.info("CPU guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Cpu> listarTodas() {
        return repository.findAll();
    }

    public Cpu buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CpuException("CPU no encontrada con ID: " + id));
    }


    public Cpu desactivarCpu(Long id) {
        log.info("Intentando desactivar CPU con ID: {}", id);
        Cpu cpu = buscarPorId(id);
        cpu.setEstado("INACTIVO");
        Cpu actualizada = repository.save(cpu);
        log.info("CPU desactivada exitosamente");
        return actualizada;
    }

}
