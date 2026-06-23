package com.BuildMyPC.msvc_component_service.Controllers;

import com.BuildMyPC.msvc_component_service.Models.Component;
import com.BuildMyPC.msvc_component_service.Models.Dtos.ComponentDTO;
import com.BuildMyPC.msvc_component_service.Services.ComponentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/components")
@Tag(name = "Catálogo de Componentes V1", description = "CRUD central para la información general de componentes")
public class ComponentController {

    private final ComponentService service;

    public ComponentController(ComponentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear componente general")
    public ResponseEntity<Component> crear(@Valid @RequestBody ComponentDTO dto) {
        return new ResponseEntity<>(service.crearComponent(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los componentes")
    public ResponseEntity<List<Component>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar componente por ID")
    public ResponseEntity<Component> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar componentes por tipo", description = "Ejemplo: CPU, GPU, RAM")
    public ResponseEntity<List<Component>> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.buscarPorTipo(tipo));
    }

    @GetMapping("/marca/{marca}")
    @Operation(summary = "Buscar componentes por marca")
    public ResponseEntity<List<Component>> buscarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(service.buscarPorMarca(marca));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar componentes por estado", description = "Ejemplo: ACTIVO, DESCONTINUADO")
    public ResponseEntity<List<Component>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.buscarPorEstado(estado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar componente", description = "Actualiza el precio base y la descripción")
    public ResponseEntity<Component> actualizar(@PathVariable Long id, @Valid @RequestBody ComponentDTO dto) {
        return ResponseEntity.ok(service.actualizarComponent(id, dto));
    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Descontinuar componente", description = "Cambia el estado a DESCONTINUADO")
    public ResponseEntity<Component> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarComponent(id));
    }
}