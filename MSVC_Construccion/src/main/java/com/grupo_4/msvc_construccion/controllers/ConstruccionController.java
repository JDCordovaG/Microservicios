package com.grupo_4.msvc_construccion.controllers;

import com.grupo_4.msvc_construccion.dtos.ConstruccionDTO;
import com.grupo_4.msvc_construccion.models.ConstruccionModel;
import com.grupo_4.msvc_construccion.services.ConstruccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/construcciones")
public class ConstruccionController {

    private final ConstruccionService service;

    public ConstruccionController(ConstruccionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConstruccionModel> crear(@Valid @RequestBody ConstruccionDTO dto) {
        return new ResponseEntity<>(service.crearConstruccion(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConstruccionModel>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstruccionModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarConstruccionBorrador(id);
        return ResponseEntity.noContent().build();
    }
}
