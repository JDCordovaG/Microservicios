package com.grupo_4.MSVC_placa_madre.controller;

import com.grupo_4.MSVC_placa_madre.dtos.Placa_madreDTO;
import com.grupo_4.MSVC_placa_madre.models.Placa_madreModel;
import com.grupo_4.MSVC_placa_madre.services.Placa_madreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/placas_madre")

public class Placa_madreController {

    private final Placa_madreService service;

    public Placa_madreController(Placa_madreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Placa_madreModel> crear(@Valid @RequestBody Placa_madreDTO dto) {
        return new ResponseEntity<>(service.crearPlacamadre(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Placa_madreModel>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placa_madreModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Placa_madreModel> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarPlacamadre(id));
    }
}
