package com.BuildMyPC.msvc_gpu_service.Controllers;

import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_gpu_service.Models.Gpu;
import com.BuildMyPC.msvc_gpu_service.Services.GpuService;
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
    public ResponseEntity<Gpu> crear(@Valid @RequestBody GpuDTO dto) {
        return new ResponseEntity<>(service.crearGpu(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Gpu>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gpu> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Gpu> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarGpu(id));
    }
}