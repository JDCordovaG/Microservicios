package com.BuildMyPC.msvc_motherboard_service.Controllers;

import com.BuildMyPC.msvc_motherboard_service.Models.Dtos.MotherboardDTO;
import com.BuildMyPC.msvc_motherboard_service.Models.Motherboard;
import com.BuildMyPC.msvc_motherboard_service.Services.MotherboardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/placas_madre")

public class MotherboardController {

    private final MotherboardService service;

    public MotherboardController(MotherboardService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Motherboard> crear(@Valid @RequestBody MotherboardDTO dto) {
        return new ResponseEntity<>(service.crearPlacamadre(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Motherboard>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motherboard> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Motherboard> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarPlacamadre(id));
    }
}
