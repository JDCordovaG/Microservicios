package com.BuildMyPC.msvc_component_service.Controllers;

import com.BuildMyPC.msvc_component_service.Models.Component;
import com.BuildMyPC.msvc_component_service.Models.Dtos.ComponentDTO;
import com.BuildMyPC.msvc_component_service.Services.ComponentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/components")

public class ComponentController {

    private final ComponentService service;

    public ComponentController(ComponentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Component> crear(@Valid @RequestBody ComponentDTO dto) {
        return new ResponseEntity<>(service.crearComponent(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Component>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Component> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Component> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarComponent(id));
    }
}
