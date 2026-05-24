package com.grupo_4.MSVC_GPU.controllers;

import com.grupo_4.MSVC_GPU.dtos.GpuDTO;
import com.grupo_4.MSVC_GPU.models.GpuModelo;
import com.grupo_4.MSVC_GPU.services.GpuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gpus")
public class GpuController {

    private final GpuService service;

    public GpuController(GpuService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GpuModelo> crear(@Valid @RequestBody GpuDTO dto) {
        return new ResponseEntity<>(service.crearGpu(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GpuModelo>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpuModelo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<GpuModelo> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarGpu(id));
    }
}
