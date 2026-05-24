package com.grupo_4.msvccomponente.services;

import com.grupo_4.msvccomponente.dtos.ComponenteDTO;
import com.grupo_4.msvccomponente.exceptions.ComponenteException;
import com.grupo_4.msvccomponente.repositories.ComponenteRepository;
import com.grupo_4.msvccomponente.models.ComponenteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponenteService {

    private static final Logger log = LoggerFactory.getLogger(ComponenteService.class);
    private final ComponenteRepository repository;

    public ComponenteService(ComponenteRepository repository) {
        this.repository = repository;
    }

    public ComponenteModel crearComponente(ComponenteDTO dto) {
        log.info("Creando nuevo componente en catálogo: {} {}", dto.getMarca(), dto.getModelo());

        ComponenteModel comp = new ComponenteModel();
        comp.setTipo(dto.getTipo().toUpperCase());
        comp.setMarca(dto.getMarca());
        comp.setModelo(dto.getModelo());
        comp.setPrecioBase(dto.getPrecioBase());
        comp.setDescripcion(dto.getDescripcion());
        comp.setFechaLanzamiento(dto.getFechaLanzamiento());
        comp.setEstado("ACTIVO");

        return repository.save(comp);
    }

    public List<ComponenteModel> listarTodos() {
        return repository.findAll();
    }

    public ComponenteModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ComponenteException("Componente no encontrado en el catálogo general con ID: " + id));
    }

    public ComponenteModel actualizarPrecioYDescripcion(Long id, Double nuevoPrecio, String nuevaDescripcion) {
        ComponenteModel comp = buscarPorId(id);
        if(nuevoPrecio != null && nuevoPrecio > 0) {
            comp.setPrecioBase(nuevoPrecio);
        }
        if(nuevaDescripcion != null) {
            comp.setDescripcion(nuevaDescripcion);
        }
        return repository.save(comp);
    }

    public ComponenteModel desactivarComponente(Long id) {
        log.info("Desactivando componente descontinuado ID: {}", id);
        ComponenteModel comp = buscarPorId(id);
        comp.setEstado("INACTIVO");
        return repository.save(comp);
    }
}
