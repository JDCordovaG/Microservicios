package com.BuildMyPC.msvc_motherboard_service.Services;

import com.BuildMyPC.msvc_motherboard_service.Models.Dtos.MotherboardDTO;
import java.util.List;

public interface MotherboardService {
    MotherboardDTO crearPlacamadre(MotherboardDTO dto);
    List<MotherboardDTO> listarTodas();
    MotherboardDTO buscarPorId(Long id);
    List<MotherboardDTO> buscarPorSocket(String socket);
    MotherboardDTO actualizarPlacamadre(Long id, MotherboardDTO dto);
    void desactivarPlacamadre(Long id);
}