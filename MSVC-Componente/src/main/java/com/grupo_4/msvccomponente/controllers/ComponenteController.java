package com.grupo_4.msvccomponente.controllers;

import com.grupo_4.msvccomponente.dtos.ComponenteDTO;
import com.grupo_4.msvccomponente.models.ComponenteModel;
import com.grupo_4.msvccomponente.services.ComponenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/componentes")
public class ComponenteController {

    private final ComponenteService service;

    public ComponenteController(ComponenteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComponenteModel> crear(@Valid @RequestBody ComponenteDTO dto) {
        return new ResponseEntity<>(service.crearComponente(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComponenteModel>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponenteModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ComponenteModel> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarComponente(id));
    }

}
