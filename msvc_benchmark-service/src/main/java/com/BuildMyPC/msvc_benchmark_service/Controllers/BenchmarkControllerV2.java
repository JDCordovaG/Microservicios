package com.BuildMyPC.msvc_benchmark_service.Controllers;

import com.BuildMyPC.msvc_benchmark_service.Assemblers.BenchmarkModelAssembler;
import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import com.BuildMyPC.msvc_benchmark_service.Services.BenchmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/benchmarks")
@RequiredArgsConstructor
@Tag(name = "Benchmarks V2", description = "Endpoints de simulación con respuestas enriquecidas con hipermedios (HATEOAS)")
public class BenchmarkControllerV2 {

    private final BenchmarkService service;
    private final BenchmarkModelAssembler assembler;

    @PostMapping
    @Operation(summary = "Calcular Benchmark con enlaces de navegación")
    public ResponseEntity<EntityModel<Benchmark>> crearBenchmark(@Valid @RequestBody BenchmarkDTO request) {
        Benchmark nuevoBenchmark = service.calcularYCrearBenchmark(request);
        return new ResponseEntity<>(assembler.toModel(nuevoBenchmark), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un benchmark autodescriptivo")
    public ResponseEntity<EntityModel<Benchmark>> buscarPorId(@PathVariable Long id) {
        Benchmark benchmark = service.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(benchmark));
    }

    @GetMapping("/build/{buildId}")
    @Operation(summary = "Listar colección de benchmarks HATEOAS por Build")
    public ResponseEntity<CollectionModel<EntityModel<Benchmark>>> listarPorBuild(@PathVariable Long buildId) {
        List<EntityModel<Benchmark>> benchmarks = service.listarPorBuild(buildId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(benchmarks,
                linkTo(methodOn(BenchmarkControllerV2.class).listarPorBuild(buildId)).withSelfRel()));
    }
}