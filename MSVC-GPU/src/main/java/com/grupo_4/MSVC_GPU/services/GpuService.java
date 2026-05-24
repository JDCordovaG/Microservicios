package com.grupo_4.MSVC_GPU.services;

import com.grupo_4.MSVC_GPU.dtos.GpuDTO;
import com.grupo_4.MSVC_GPU.exceptions.GpuException;
import com.grupo_4.MSVC_GPU.models.GpuModelo;
import com.grupo_4.MSVC_GPU.repositories.GpuRepository;
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

    public GpuModelo crearGpu(GpuDTO dto) {
        log.info("Iniciando creación de GPU del fabricante: {}", dto.getFabricanteChip());

        GpuModelo gpu = new GpuModelo();
        gpu.setComponenteId(dto.getComponenteId());
        gpu.setVramGb(dto.getVramGb());
        gpu.setTipoMemoria(dto.getTipoMemoria());
        gpu.setTdpWatts(dto.getTdpWatts());
        gpu.setLargoMm(dto.getLargoMm());
        gpu.setPuntajeBase(dto.getPuntajeBase());
        gpu.setFabricanteChip(dto.getFabricanteChip());
        gpu.setEstado("ACTIVO");

        GpuModelo guardada = repository.save(gpu);
        log.info("GPU guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<GpuModelo> listarTodas() {
        return repository.findAll();
    }

    public GpuModelo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GpuException("GPU no encontrada con ID: " + id));
    }

    public GpuModelo desactivarGpu(Long id) {
        log.info("Intentando desactivar GPU con ID: {}", id);
        GpuModelo gpu = buscarPorId(id);
        gpu.setEstado("INACTIVO");
        GpuModelo actualizada = repository.save(gpu);
        log.info("GPU desactivada exitosamente");
        return actualizada;
    }

}
