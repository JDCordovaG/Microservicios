package com.BuildMyPC.msvc_compatibility_service.Controllers;

import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.CompatibilityRequestDTO;
import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import com.BuildMyPC.msvc_compatibility_service.Services.CompatibilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/compatibilities")
@Tag(name = "Compatibilidad V1", description = "Motor de validación de componentes")
public class CompatibilityController {

    private final CompatibilityService service;

    public CompatibilityController(CompatibilityService service) {
        this.service = service;
    }

    @PostMapping("/validar")
    @Operation(summary = "Crear validación", description = "Evalúa las reglas de negocio entre componentes")
    @ApiResponse(responseCode = "201", description = "Validación generada")
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody CompatibilityRequestDTO dto) {
        ValidacionCompatibility validacion = service.crearValidacion(dto);

        // Devolvemos el objeto completo más un flag rápido para el build-service
        return new ResponseEntity<>(Map.of(
                "compatible", validacion.getCompatible(),
                "validacionId", validacion.getId(),
                "detalle", validacion
        ), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas las validaciones")
    public ResponseEntity<List<ValidacionCompatibility>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar validación por ID")
    public ResponseEntity<ValidacionCompatibility> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/build/{buildId}")
    @Operation(summary = "Buscar validación por ID de Build")
    public ResponseEntity<ValidacionCompatibility> buscarPorBuildId(@PathVariable Long buildId) {
        return ResponseEntity.ok(service.buscarPorBuildId(buildId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar validación temporal o histórica")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarValidacion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}