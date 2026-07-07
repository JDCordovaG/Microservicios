package com.BuildMyPC.msvc_gpu_service.Services;

import com.BuildMyPC.msvc_gpu_service.Exceptions.GpuException;
import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_gpu_service.Models.Gpu;
import com.BuildMyPC.msvc_gpu_service.Repositories.GpuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GpuService {

    private static final Logger log = LoggerFactory.getLogger(GpuService.class);
    private final GpuRepository repository;

    public GpuService(GpuRepository repository) {
        this.repository = repository;
    }

    public Gpu crearGpu(GpuDTO dto) {
        log.info("Iniciando creación de del fabricante: {}", dto.getFabricanteChip());

        Gpu gpu = new Gpu();
        gpu.setComponenteId(dto.getComponenteId());
        gpu.setVramGb(dto.getVramGb());
        gpu.setTipoMemoria(dto.getTipoMemoria());
        gpu.setTdpWatts(dto.getTdpWatts());
        gpu.setLargoMm(dto.getLargoMm());
        gpu.setPuntajeBase(dto.getPuntajeBase());
        gpu.setFabricanteChip(dto.getFabricanteChip());
        gpu.setEstado("ACTIVO");

        Gpu guardada = repository.save(gpu);
        log.info("GPU guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Gpu> listarTodas() {
        return repository.findAll();
    }

    public Gpu buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GpuException("GPU no encontrada con ID: " + id));
    }

    public Gpu desactivarGpu(Long id) {
        log.info("Intentando desactivar GPU con ID: {}", id);
        Gpu gpu = buscarPorId(id);
        gpu.setEstado("INACTIVO");
        Gpu actualizada = repository.save(gpu);
        log.info("GPU desactivada exitosamente");
        return actualizada;
    }

}
