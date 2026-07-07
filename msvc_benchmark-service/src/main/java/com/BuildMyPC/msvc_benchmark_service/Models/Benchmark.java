package com.BuildMyPC.msvc_benchmark_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "benchmarks")
public class Benchmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benchmark_id")
    private Long id;

    @Column(nullable = false, name = "build_id")
    private Long buildId;

    @Column(nullable = false, name = "puntaje_cpu_benchmark")
    private Integer puntajeCpu;

    @Column(nullable = false, name = "puntaje_gpu_benchmark")
    private Integer puntajeGpu;

    @Column(nullable = false, name = "puntaje_total_benchmark")
    private Integer puntajeTotal;

    @Column(nullable = false, name = "categoria_uso_benchmark")
    private String categoriaUso;

    @Column(nullable = false, name = "fecha_calculo_benchmark")
    private LocalDate fechaCalculo;

}
