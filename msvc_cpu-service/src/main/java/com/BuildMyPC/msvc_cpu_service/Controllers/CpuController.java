package com.BuildMyPC.msvc_cpu_service.Controllers;

import com.BuildMyPC.msvc_cpu_service.Models.Dtos.CpuDTO;
import com.BuildMyPC.msvc_cpu_service.Services.CpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cpus")
@Tag(name = "Catálogo CPU - V1", description = "Endpoints CRUD estándar para procesadores")
public class CpuController {

    private final CpuService cpuService;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @PostMapping
    @Operation(summary = "Registrar un procesador", description = "Inserta un procesador validando socket obligatorio y TDP energético positivo")
    @ApiResponse(responseCode = "201", description = "Procesador añadido exitosamente")
    public ResponseEntity<CpuDTO> crear(@Valid @RequestBody CpuDTO cpuDTO) {
        return new ResponseEntity<>(cpuService.guardar(cpuDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CpuDTO>> obtenerTodos() {
        return ResponseEntity.ok(cpuService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CpuDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cpuService.buscarPorId(id));
    }

    @GetMapping("/filtrar/socket")
    public ResponseEntity<List<CpuDTO>> obtenerPorSocket(@RequestParam String socket) {
        return ResponseEntity.ok(cpuService.listarPorSocket(socket));
    }

    @GetMapping("/filtrar/generacion")
    public ResponseEntity<List<CpuDTO>> obtenerPorGeneracion(@RequestParam String generacion) {
        return ResponseEntity.ok(cpuService.listarPorGeneracion(generacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CpuDTO> modificar(@PathVariable Long id, @Valid @RequestBody CpuDTO cpuDTO) {
        return ResponseEntity.ok(cpuService.actualizar(id, cpuDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Baja lógica de CPU", description = "Cambia el estado activo a falso para evitar romper configuraciones históricas")
    @ApiResponse(responseCode = "204", description = "CPU deshabilitada del catálogo comercial")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cpuService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}