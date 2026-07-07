package com.BuildMyPC.msvc_benchmark_service.Services;

import com.BuildMyPC.msvc_benchmark_service.Exceptions.BenchmarkException;
import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import com.BuildMyPC.msvc_benchmark_service.Repositories.BenchmarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BenchmarkService {

    private static final Logger log = LoggerFactory.getLogger(BenchmarkService.class);
    private final BenchmarkRepository repository;

    public BenchmarkService(BenchmarkRepository repository) {
        this.repository = repository;
    }


    public Benchmark crearBenchmark(BenchmarkDTO dto) {
        log.info("Iniciando creación de benchmark con id de build: {}", dto.getBuildId());
        Benchmark benchmark = new Benchmark();
        benchmark.setBuildId(dto.getBuildId());
        benchmark.setPuntajeCpu(dto.getPuntajeCpu());
        benchmark.setPuntajeGpu(dto.getPuntajeGpu());
        benchmark.setPuntajeTotal(dto.getPuntajeTotal());
        benchmark.setCategoriaUso(dto.getCategoriaUso());
        benchmark.setFechaCalculo(dto.getFechaCalculo());

        Benchmark guardada = repository.save(benchmark);
        log.info("Benchmark guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Benchmark> listarTodas() {
        return repository.findAll();
    }

    public Benchmark buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BenchmarkException("benchmark no encontrada con ID: " + id));
    }

}
