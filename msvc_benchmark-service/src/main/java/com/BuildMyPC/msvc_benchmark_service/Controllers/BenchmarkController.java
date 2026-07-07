package com.BuildMyPC.msvc_benchmark_service.Controllers;

import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import com.BuildMyPC.msvc_benchmark_service.Services.BenchmarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benchmarks")

public class BenchmarkController {

    private final BenchmarkService service;

    public BenchmarkController(BenchmarkService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Benchmark> crear(@Valid @RequestBody BenchmarkDTO dto) {
        return new ResponseEntity<>(service.crearBenchmark(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Benchmark>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Benchmark> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

}
