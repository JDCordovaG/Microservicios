package com.BuildMyPC.msvc_build_service.Controllers;

import com.BuildMyPC.msvc_build_service.Models.Build;
import com.BuildMyPC.msvc_build_service.Models.Dtos.BuildDTO;
import com.BuildMyPC.msvc_build_service.Services.BuildService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/builds")
@Tag(name="Builds V1", description = "Metodos CRUD para la gestión de configuraciones de PC")
public class BuildController {

    private final BuildService service;

    public BuildController(BuildService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear nueva Build", description = "Crea una build en estado borrador y evalúa su compatibilidad")
    @ApiResponse(responseCode = "201", description = "Build creada exitosamente")
    public ResponseEntity<Build> crear(@Valid @RequestBody BuildDTO dto) {
        return new ResponseEntity<>(service.crearBuild(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas las Builds")
    public ResponseEntity<List<Build>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Build por ID")
    public ResponseEntity<Build> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar componentes de una Build", description = "Actualiza los componentes y reinicia el estado a BORRADOR")
    public ResponseEntity<Build> actualizar(@PathVariable Long id, @Valid @RequestBody BuildDTO dto) {
        return ResponseEntity.ok(service.actualizarBuild(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Build en borrador", description = "Elimina físicamente la build solo si se encuentra en estado BORRADOR")
    @ApiResponse(responseCode = "204", description = "Build eliminada")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarBuildBorrador(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}