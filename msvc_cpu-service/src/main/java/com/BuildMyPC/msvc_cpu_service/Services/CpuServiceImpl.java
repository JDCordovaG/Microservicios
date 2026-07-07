package com.BuildMyPC.msvc_cpu_service.Services;

import com.BuildMyPC.msvc_cpu_service.Clients.ComponentClient;
import com.BuildMyPC.msvc_cpu_service.Exceptions.CpuException;
import com.BuildMyPC.msvc_cpu_service.Models.Cpu;
import com.BuildMyPC.msvc_cpu_service.Models.Dtos.CpuDTO;
import com.BuildMyPC.msvc_cpu_service.Repositories.CpuRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CpuServiceImpl implements CpuService {

    private static final Logger log = LoggerFactory.getLogger(CpuServiceImpl.class);
    private final CpuRepository cpuRepository;
    private final ComponentClient componentClient;

    public CpuServiceImpl(CpuRepository cpuRepository, ComponentClient componentClient) {
        this.cpuRepository = cpuRepository;
        this.componentClient = componentClient;
    }

    @Override
    @Transactional
    public CpuDTO guardar(CpuDTO dto) {
        log.info("Iniciando validación cruzada para CPU con componenteId: {}", dto.getComponenteId());

        try {
            // Se comunica por red (HTTP) con el msvc-component-service
            componentClient.obtenerComponentePorId(dto.getComponenteId());
        } catch (FeignException e) {
            log.error("Fallo de integridad: El componente base ID {} no existe.", dto.getComponenteId());
            throw new CpuException("El componente base no existe en el catálogo principal. Registre el componente primero.");
        }

        Cpu cpu = modelToEntity(dto);
        cpu.setActivo(true);
        return entityToModel(cpuRepository.save(cpu));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CpuDTO> listarTodos() {
        log.info("Consultando catálogo completo de procesadores activos");
        return cpuRepository.findByActivoTrue().stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CpuDTO buscarPorId(Long id) {
        log.info("Buscando CPU con ID: {}", id);
        Cpu cpu = cpuRepository.findById(id)
                .filter(Cpu::getActivo)
                .orElseThrow(() -> {
                    log.error("Fallo al consultar CPU: ID {} no existe o está inactivo", id);
                    return new CpuException("Procesador no encontrado o descontinuado con el ID provisto");
                });
        return entityToModel(cpu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CpuDTO> listarPorSocket(String socket) {
        log.info("Filtrando procesadores por socket: {}", socket);
        return cpuRepository.findBySocketIgnoreCaseAndActivoTrue(socket).stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CpuDTO> listarPorGeneracion(String generacion) {
        log.info("Filtrando procesadores por generación: {}", generacion);
        return cpuRepository.findByGeneracionIgnoreCaseAndActivoTrue(generacion).stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CpuDTO> listarPorComponentes(List<Long> componenteIds) {
        log.info("Filtrando procesadores cruzados por marcas validadas de Component Service");
        return cpuRepository.findByComponenteIdInAndActivoTrue(componenteIds).stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CpuDTO actualizar(Long id, CpuDTO dto) {
        log.info("Iniciando actualización técnica del procesador con ID: {}", id);
        Cpu cpuExistente = cpuRepository.findById(id)
                .orElseThrow(() -> new CpuException("Imposible actualizar: CPU inexistente"));

        cpuExistente.setSocket(dto.getSocket());
        cpuExistente.setNucleos(dto.getNucleos());
        cpuExistente.setHilos(dto.getHilos());
        cpuExistente.setFrecuenciaBase(dto.getFrecuenciaBase());
        cpuExistente.setFrecuenciaTurbo(dto.getFrecuenciaTurbo());
        cpuExistente.setTdpWatts(dto.getTdpWatts());
        cpuExistente.setGeneracion(dto.getGeneracion());
        cpuExistente.setSoportaDdr4(dto.getSoportaDdr4());
        cpuExistente.setSoportaDdr5(dto.getSoportaDdr5());

        Cpu actualizada = cpuRepository.save(cpuExistente);
        log.info("Características de CPU ID: {} modificadas con éxito", actualizada.getId());
        return entityToModel(actualizada);
    }

    @Override
    @Transactional
    public void desactivar(Long id) {
        log.warn("Solicitud de baja lógica para CPU ID: {}", id);
        Cpu cpu = cpuRepository.findById(id)
                .orElseThrow(() -> new CpuException("Imposible dar de baja: CPU inexistente"));
        cpu.setActivo(false);
        cpuRepository.save(cpu);
        log.info("El procesador ID: {} ha sido marcado correctamente como INACTIVO/DESCONTINUADO", id);
    }

    private Cpu modelToEntity(CpuDTO dto) {
        Cpu entity = new Cpu();
        entity.setId(dto.getId());
        entity.setComponenteId(dto.getComponenteId());
        entity.setSocket(dto.getSocket());
        entity.setNucleos(dto.getNucleos());
        entity.setHilos(dto.getHilos());
        entity.setFrecuenciaBase(dto.getFrecuenciaBase());
        entity.setFrecuenciaTurbo(dto.getFrecuenciaTurbo());
        entity.setTdpWatts(dto.getTdpWatts());
        entity.setGeneracion(dto.getGeneracion());
        entity.setSoportaDdr4(dto.getSoportaDdr4());
        entity.setSoportaDdr5(dto.getSoportaDdr5());
        entity.setActivo(dto.getActivo());
        return entity;
    }

    private CpuDTO entityToModel(Cpu entity) {
        CpuDTO dto = new CpuDTO();
        dto.setId(entity.getId());
        dto.setComponenteId(entity.getComponenteId());
        dto.setSocket(entity.getSocket());
        dto.setNucleos(entity.getNucleos());
        dto.setHilos(entity.getHilos());
        dto.setFrecuenciaBase(entity.getFrecuenciaBase());
        dto.setFrecuenciaTurbo(entity.getFrecuenciaTurbo());
        dto.setTdpWatts(entity.getTdpWatts());
        dto.setGeneracion(entity.getGeneracion());
        dto.setSoportaDdr4(entity.getSoportaDdr4());
        dto.setSoportaDdr5(entity.getSoportaDdr5());
        dto.setActivo(entity.getActivo());
        return dto;
    }
}