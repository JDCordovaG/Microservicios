package com.BuildMyPC.msvc_power_supply_service.Services;

import com.BuildMyPC.msvc_power_supply_service.Models.Dtos.PowersupplyDTO;
import java.util.List;

public interface PowersupplyService {
    PowersupplyDTO crearFuentePoder(PowersupplyDTO dto);
    List<PowersupplyDTO> listarTodas();
    PowersupplyDTO buscarPorId(Long id);
    List<PowersupplyDTO> buscarPorCertificacion(String certificacion);
    PowersupplyDTO actualizarFuentePoder(Long id, PowersupplyDTO dto);
    void desactivarFuentePoder(Long id);
}