package com.BuildMyPC.msvc_gpu_service.Controllers;

import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_gpu_service.Services.GpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gpus")
@Tag(name = "Catálogo GPU - V1", description = "Endpoints para la gestión de tarjetas de video")
public class GpuController {

    private final GpuService service;

    public GpuController(GpuService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Registrar una GPU", description = "Añade una nueva tarjeta gráfica al catálogo")
    @ApiResponse(responseCode = "201", description = "GPU creada existosamente")
    public ResponseEntity<GpuDTO> crear(@Valid @RequestBody GpuDTO dto) {
        return new ResponseEntity<>(service.crearGpu(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GpuDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpuDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/fabricante/{fabricanteChip}")
    public ResponseEntity<List<GpuDTO>> buscarPorFabricante(@PathVariable String fabricanteChip) {
        return ResponseEntity.ok(service.buscarPorFabricante(fabricanteChip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GpuDTO> actualizar(@PathVariable Long id, @Valid @RequestBody GpuDTO dto) {
        return ResponseEntity.ok(service.actualizarGpu(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Baja lógica de GPU", description = "Desactiva una GPU para no romper compatibilidades previas")
    @ApiResponse(responseCode = "204", description = "GPU desactivada")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        service.desactivarGpu(id);
        return ResponseEntity.noContent().build();
    }
}