package com.BuildMyPC.msvc_benchmark_service.Controllers;

import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import com.BuildMyPC.msvc_benchmark_service.Services.BenchmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benchmarks")
@RequiredArgsConstructor
@Tag(name = "Benchmarks V1", description = "Endpoints de gestión de estimación de rendimiento y benchmarks tradicionales")
public class BenchmarkController {

    private final BenchmarkService service;

    @PostMapping
    @Operation(summary = "Calcular y guardar un Benchmark", description = "Obtiene los datos de la build remota y sus componentes para calcular la estimación matemática de rendimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Benchmark calculado y registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o la build no está lista")
    })
    public ResponseEntity<Benchmark> crearBenchmark(@Valid @RequestBody BenchmarkDTO request) {
        return new ResponseEntity<>(service.calcularYCrearBenchmark(request), HttpStatus.CREATED);
    }

    @GetMapping("/build/{buildId}")
    @Operation(summary = "Listar por ID de Build", description = "Retorna el historial de simulaciones de rendimiento asociadas a una configuración de PC.")
    @ApiResponse(responseCode = "200", description = "Lista devuelta correctamente")
    public ResponseEntity<List<Benchmark>> listarPorBuild(
            @Parameter(description = "ID de la build a consultar", required = true, example = "12")
            @PathVariable Long buildId) {
        return ResponseEntity.ok(service.listarPorBuild(buildId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un benchmark por su ID", description = "Retorna el detalle técnico de un cálculo específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benchmark encontrado"),
            @ApiResponse(responseCode = "404", description = "El identificador no existe en la base de datos")
    })
    public ResponseEntity<Benchmark> buscarPorId(
            @Parameter(description = "ID único del benchmark", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro de benchmark", description = "Borra físicamente la simulación del sistema.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado con éxito")
    public ResponseEntity<Void> eliminarBenchmark(@PathVariable Long id) {
        service.eliminarBenchmark(id);
        return ResponseEntity.noContent().build();
    }
}