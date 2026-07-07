package com.BuildMyPC.msvc_motherboard_service.Services;

import com.BuildMyPC.msvc_motherboard_service.Exceptions.MotherboardException;
import com.BuildMyPC.msvc_motherboard_service.Models.Dtos.MotherboardDTO;
import com.BuildMyPC.msvc_motherboard_service.Models.Motherboard;
import com.BuildMyPC.msvc_motherboard_service.Repositories.MotherboardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotherboardService {

    private static final Logger log = LoggerFactory.getLogger(MotherboardService.class);
    private final MotherboardRepository repository;

    public MotherboardService(MotherboardRepository repository) {
        this.repository = repository;
    }

    public Motherboard crearPlacamadre(MotherboardDTO dto) {
        log.info("Creando placa madre con socket: {} y chipset: {}", dto.getSocket(), dto.getChipset());

        Motherboard board = new Motherboard();
        board.setComponenteId(dto.getComponenteId());
        board.setSocket(dto.getSocket());
        board.setChipset(dto.getChipset());
        board.setTipoRamSoportada(dto.getTipoRamSoportada());
        board.setSlotsRam(dto.getSlotsRam());
        board.setMaxRamGb(dto.getMaxRamGb());
        board.setFormato(dto.getFormato());
        board.setEstado("ACTIVO");

        Motherboard guardada = repository.save(board);
        log.info("Placa madre guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Motherboard> listarTodas() {
        return repository.findAll();
    }

    public Motherboard buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MotherboardException("Placa madre no encontrada con ID: " + id));
    }

    public Motherboard desactivarPlacamadre(Long id) {
        log.info("Intentando desactivar Placa Madre con ID: {}", id);
        Motherboard board = buscarPorId(id);
        board.setEstado("INACTIVO");
        return repository.save(board);
    }
}
