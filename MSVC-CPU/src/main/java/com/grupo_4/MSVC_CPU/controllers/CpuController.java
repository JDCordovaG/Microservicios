package com.grupo_4.MSVC_CPU.controllers;

import com.grupo_4.MSVC_CPU.models.Cpu;
import com.grupo_4.MSVC_CPU.dtos.CpuDTO;
import com.grupo_4.MSVC_CPU.services.CpuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cpus")

public class CpuController {

    private final CpuService service;

    public CpuController(CpuService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cpu> crear(@Valid @RequestBody CpuDTO dto) {
        return new ResponseEntity<>(service.crearCpu(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cpu>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cpu> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Cpu> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarCpu(id));
    }
}
