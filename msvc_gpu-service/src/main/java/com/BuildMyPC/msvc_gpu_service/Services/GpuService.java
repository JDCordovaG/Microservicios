package com.BuildMyPC.msvc_gpu_service.Services;

import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import java.util.List;

public interface GpuService {
    GpuDTO crearGpu(GpuDTO dto);
    List<GpuDTO> listarTodas();
    GpuDTO buscarPorId(Long id);
    List<GpuDTO> buscarPorFabricante(String fabricante);
    GpuDTO actualizarGpu(Long id, GpuDTO dto);
    void desactivarGpu(Long id);
}