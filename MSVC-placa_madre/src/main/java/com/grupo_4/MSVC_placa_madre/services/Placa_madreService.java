package com.grupo_4.MSVC_placa_madre.services;

import com.grupo_4.MSVC_placa_madre.dtos.Placa_madreDTO;
import com.grupo_4.MSVC_placa_madre.exceptions.Placa_madreException;
import com.grupo_4.MSVC_placa_madre.models.Placa_madreModel;
import com.grupo_4.MSVC_placa_madre.repositories.Placa_madreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Placa_madreService {

    private static final Logger log = LoggerFactory.getLogger(Placa_madreService.class);
    private final Placa_madreRepository repository;

    public Placa_madreService(Placa_madreRepository repository) {
        this.repository = repository;
    }

    public Placa_madreModel crearPlacamadre(Placa_madreDTO dto) {
        log.info("Creando placa madre con socket: {} y chipset: {}", dto.getSocket(), dto.getChipset());

        Placa_madreModel board = new Placa_madreModel();
        board.setComponenteId(dto.getComponenteId());
        board.setSocket(dto.getSocket());
        board.setChipset(dto.getChipset());
        board.setTipoRamSoportada(dto.getTipoRamSoportada());
        board.setSlotsRam(dto.getSlotsRam());
        board.setMaxRamGb(dto.getMaxRamGb());
        board.setFormato(dto.getFormato());
        board.setEstado("ACTIVO");

        Placa_madreModel guardada = repository.save(board);
        log.info("Placa madre guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Placa_madreModel> listarTodas() {
        return repository.findAll();
    }

    public Placa_madreModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new Placa_madreException("Placa madre no encontrada con ID: " + id));
    }

    public Placa_madreModel desactivarPlacamadre(Long id) {
        log.info("Intentando desactivar Placa Madre con ID: {}", id);
        Placa_madreModel board = buscarPorId(id);
        board.setEstado("INACTIVO");
        return repository.save(board);
    }
}
