package com.BuildMyPC.msvc_compatibility_service.Controllers;

import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.CompatibilityDTO;
import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import com.BuildMyPC.msvc_compatibility_service.Services.CompatibilityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compatibilities")

public class CompatibilityController {

    private final CompatibilityService service;

    public CompatibilityController(CompatibilityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ValidacionCompatibility> crear(@Valid @RequestBody CompatibilityDTO dto) {
        return new ResponseEntity<>(service.crearCompatibility(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ValidacionCompatibility>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidacionCompatibility> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}