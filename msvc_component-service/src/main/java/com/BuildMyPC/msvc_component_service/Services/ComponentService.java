package com.BuildMyPC.msvc_component_service.Services;

import com.BuildMyPC.msvc_component_service.Exceptions.ComponentException;
import com.BuildMyPC.msvc_component_service.Models.Component;
import com.BuildMyPC.msvc_component_service.Models.Dtos.ComponentDTO;
import com.BuildMyPC.msvc_component_service.Repositories.ComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ComponentService {

    private static final Logger log = LoggerFactory.getLogger(ComponentService.class);
    private final ComponentRepository repository;

    public ComponentService(ComponentRepository repository) {
        this.repository = repository;
    }

    public Component crearComponent(ComponentDTO dto) {
        log.info("Iniciando creación de Componente con marca: {}", dto.getMarca());
        Component component = new Component();
        component.setTipo(dto.getTipo());
        component.setMarca(dto.getMarca());
        component.setModelo(dto.getModelo());
        component.setPrecioBase(dto.getPrecioBase());
        component.setDescripcion(dto.getDescripcion());
        component.setFechaLanzamiento(dto.getFechaLanzamiento());
        component.setEstado("ACTIVO");

        Component guardada = repository.save(component);
        log.info("Componente guardado exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Component> listarTodas() {
        return repository.findAll();
    }

    public Component buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ComponentException("Componente no encontrada con ID: " + id));
    }


    public Component desactivarComponent(Long id) {
        log.info("Intentando desactivar Componente con ID: {}", id);
        Component component = buscarPorId(id);
        component.setEstado("INACTIVO");
        Component actualizada = repository.save(component);
        log.info("Componente desactivado exitosamente");
        return actualizada;
    }

}