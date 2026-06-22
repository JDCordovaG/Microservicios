package com.BuildMyPC.msvc_benchmark_service.Services;

import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import java.util.List;

public interface BenchmarkService {
    Benchmark calcularYCrearBenchmark(BenchmarkDTO request);
    List<Benchmark> listarPorBuild(Long buildId);
    Benchmark buscarPorId(Long id);
    void eliminarBenchmark(Long id);
}