package com.BuildMyPC.msvc_benchmark_service.Services;

import com.BuildMyPC.msvc_benchmark_service.Clients.BuildClient;
import com.BuildMyPC.msvc_benchmark_service.Clients.CpuClient;
import com.BuildMyPC.msvc_benchmark_service.Clients.GpuClient;
import com.BuildMyPC.msvc_benchmark_service.Exceptions.BenchmarkException;
import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BenchmarkDTO;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BuildDTO;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.CpuDTO;
import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_benchmark_service.Repositories.BenchmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BenchmarkServiceImpl implements BenchmarkService {

    private final BenchmarkRepository repository;
    private final BuildClient buildClient;
    private final CpuClient cpuClient;
    private final GpuClient gpuClient;

    @Transactional
    @Override
    public Benchmark calcularYCrearBenchmark(BenchmarkDTO request) {
        log.info("Iniciando cálculo de benchmark para la build ID: {}", request.getBuildId());

        BuildDTO build = buildClient.getBuildById(request.getBuildId());
        if (build == null || !"COMPLETA".equalsIgnoreCase(build.getEstado())) {
            throw new BenchmarkException("La build no existe o no está en estado COMPLETA para calcular su rendimiento.");
        }

        CpuDTO cpu = cpuClient.getCpuById(build.getCpuId());
        GpuDTO gpu = gpuClient.getGpuById(build.getGpuId());

        int puntajeCpu = calcularPuntajeCpu(cpu, request.getCategoriaUso());
        int puntajeGpu = calcularPuntajeGpu(gpu, request.getCategoriaUso());
        double puntajeTotal = (puntajeCpu + puntajeGpu) / 2.0;

        Benchmark benchmark = Benchmark.builder()
                .buildId(request.getBuildId())
                .puntajeCpu((double) puntajeCpu)
                .puntajeGpu((double) puntajeGpu)
                .puntajeTotal(puntajeTotal)
                .categoriaUso(request.getCategoriaUso())
                .build();

        log.info("Benchmark calculado exitosamente. Puntaje Total: {}", puntajeTotal);
        return repository.save(benchmark);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Benchmark> listarPorBuild(Long buildId) {
        return repository.findByBuildId(buildId);
    }

    @Transactional(readOnly = true)
    @Override
    public Benchmark buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BenchmarkException("Benchmark no encontrado con ID: " + id));
    }

    @Transactional
    @Override
    public void eliminarBenchmark(Long id) {
        if (!repository.existsById(id)) {
            throw new BenchmarkException("No se puede eliminar. Benchmark no encontrado con ID: " + id);
        }
        log.warn("Eliminando benchmark con ID: {}", id);
        repository.deleteById(id);
    }

    private int calcularPuntajeCpu(CpuDTO cpu, String categoria) {
        return (cpu.getNucleos() * 100) + (int)(cpu.getFrecuenciaBase() * 10);
    }

    private int calcularPuntajeGpu(GpuDTO gpu, String categoria) {
        return gpu.getPuntajeBase() != null ? gpu.getPuntajeBase() + (gpu.getVramGb() * 50) : (gpu.getVramGb() * 100);
    }
}