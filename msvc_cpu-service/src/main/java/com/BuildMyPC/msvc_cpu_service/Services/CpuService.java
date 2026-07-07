package com.BuildMyPC.msvc_cpu_service.Services;

import com.BuildMyPC.msvc_cpu_service.Models.Dtos.CpuDTO;
import java.util.List;

public interface CpuService {
    CpuDTO guardar(CpuDTO cpuDTO);
    List<CpuDTO> listarTodos();
    CpuDTO buscarPorId(Long id);
    List<CpuDTO> listarPorSocket(String socket);
    List<CpuDTO> listarPorGeneracion(String generacion);
    List<CpuDTO> listarPorComponentes(List<Long> componenteIds);
    CpuDTO actualizar(Long id, CpuDTO cpuDTO);
    void desactivar(Long id);
}