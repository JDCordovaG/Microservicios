package com.BuildMyPC.msvc_gpu_service.Services;

import com.BuildMyPC.msvc_gpu_service.Clients.ComponentClient;
import com.BuildMyPC.msvc_gpu_service.Exceptions.GpuException;
import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_gpu_service.Models.Gpu;
import com.BuildMyPC.msvc_gpu_service.Repositories.GpuRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GpuServiceImpl implements GpuService {

    private static final Logger log = LoggerFactory.getLogger(GpuServiceImpl.class);
    private final GpuRepository repository;
    private final ComponentClient componentClient;

    public GpuServiceImpl(GpuRepository repository, ComponentClient componentClient) {
        this.repository = repository;
        this.componentClient = componentClient;
    }

    @Override
    @Transactional
    public GpuDTO crearGpu(GpuDTO dto) {
        log.info("Validando en Component-Service la existencia de la GPU base...");

        try {
            componentClient.obtenerComponentePorId(dto.getComponenteId());
        } catch (FeignException e) {
            log.error("Validación fallida: Componente ID {} inexistente.", dto.getComponenteId());
            throw new GpuException("Fallo de integridad relacional: El componente general no está registrado.");
        }

        Gpu gpu = mapToEntity(dto);
        gpu.setActivo(true);
        return mapToDTO(repository.save(gpu));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GpuDTO> listarTodas() {
        log.info("Consultando catálogo completo de tarjetas gráficas activas");
        return repository.findByActivoTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GpuDTO buscarPorId(Long id) {
        log.info("Buscando GPU activa con ID: {}", id);
        return repository.findByIdAndActivoTrue(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new GpuException("GPU no encontrada o descontinuada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GpuDTO> buscarPorFabricante(String fabricante) {
        log.info("Filtrando GPUs por fabricante de chip: {}", fabricante);
        return repository.findByFabricanteChipIgnoreCaseAndActivoTrue(fabricante).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GpuDTO actualizarGpu(Long id, GpuDTO dto) {
        log.info("Actualizando especificaciones de GPU ID: {}", id);
        Gpu gpuExistente = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new GpuException("Imposible actualizar: GPU inexistente o inactiva"));

        gpuExistente.setVramGb(dto.getVramGb());
        gpuExistente.setTipoMemoria(dto.getTipoMemoria());
        gpuExistente.setTdpWatts(dto.getTdpWatts());
        gpuExistente.setLargoMm(dto.getLargoMm());
        gpuExistente.setPuntajeBase(dto.getPuntajeBase());
        gpuExistente.setFabricanteChip(dto.getFabricanteChip());

        return mapToDTO(repository.save(gpuExistente));
    }

    @Override
    @Transactional
    public void desactivarGpu(Long id) {
        log.warn("Solicitando baja lógica para GPU ID: {}", id);
        Gpu gpu = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new GpuException("Imposible dar de baja: GPU no encontrada"));
        gpu.setActivo(false);
        repository.save(gpu);
        log.info("GPU ID: {} desactivada correctamente del catálogo comercial", id);
    }

    private Gpu mapToEntity(GpuDTO dto) {
        Gpu gpu = new Gpu();
        gpu.setComponenteId(dto.getComponenteId());
        gpu.setVramGb(dto.getVramGb());
        gpu.setTipoMemoria(dto.getTipoMemoria());
        gpu.setTdpWatts(dto.getTdpWatts());
        gpu.setLargoMm(dto.getLargoMm());
        gpu.setPuntajeBase(dto.getPuntajeBase());
        gpu.setFabricanteChip(dto.getFabricanteChip());
        return gpu;
    }

    private GpuDTO mapToDTO(Gpu gpu) {
        GpuDTO dto = new GpuDTO();
        dto.setId(gpu.getId());
        dto.setComponenteId(gpu.getComponenteId());
        dto.setVramGb(gpu.getVramGb());
        dto.setTipoMemoria(gpu.getTipoMemoria());
        dto.setTdpWatts(gpu.getTdpWatts());
        dto.setLargoMm(gpu.getLargoMm());
        dto.setPuntajeBase(gpu.getPuntajeBase());
        dto.setFabricanteChip(gpu.getFabricanteChip());
        dto.setActivo(gpu.getActivo());
        return dto;
    }
}