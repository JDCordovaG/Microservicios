package com.BuildMyPC.msvc_component_service.Services;

import com.BuildMyPC.msvc_component_service.Exceptions.ComponentException;
import com.BuildMyPC.msvc_component_service.Models.Component;
import com.BuildMyPC.msvc_component_service.Models.Dtos.ComponentDTO;
import com.BuildMyPC.msvc_component_service.Repositories.ComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComponentService {

    private static final Logger log = LoggerFactory.getLogger(ComponentService.class);
    private final ComponentRepository repository;

    public ComponentService(ComponentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Component crearComponent(ComponentDTO dto) {
        log.info("Iniciando creación de Componente: {} {}", dto.getMarca(), dto.getModelo());

        Component component = new Component();
        component.setTipo(dto.getTipo().toUpperCase());
        component.setMarca(dto.getMarca());
        component.setModelo(dto.getModelo());
        component.setPrecioBase(dto.getPrecioBase());
        component.setDescripcion(dto.getDescripcion());

        Component guardada = repository.save(component);
        log.info("Componente guardado exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    @Transactional(readOnly = true)
    public List<Component> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Component buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ComponentException("Componente no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Component> buscarPorTipo(String tipo) {
        return repository.findByTipo(tipo.toUpperCase());
    }

    @Transactional(readOnly = true)
    public List<Component> buscarPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<Component> buscarPorEstado(String estado) {
        return repository.findByEstado(estado.toUpperCase());
    }

    @Transactional
    public Component actualizarComponent(Long id, ComponentDTO dto) {
        log.info("Actualizando Componente con ID: {}", id);
        Component component = buscarPorId(id);

        component.setPrecioBase(dto.getPrecioBase());
        component.setDescripcion(dto.getDescripcion());

        return repository.save(component);
    }

    @Transactional
    public Component desactivarComponent(Long id) {
        log.info("Desactivando Componente con ID: {}", id);
        Component component = buscarPorId(id);
        component.setEstado("DESCONTINUADO");
        Component actualizada = repository.save(component);
        log.info("Componente marcado como DESCONTINUADO exitosamente");
        return actualizada;
    }
}