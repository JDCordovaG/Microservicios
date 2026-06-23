package com.BuildMyPC.msvc_benchmark_service.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "benchmarks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benchmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long buildId;

    @Column(nullable = false)
    private Double puntajeCpu;

    @Column(nullable = false)
    private Double puntajeGpu;

    @Column(nullable = false)
    private Double puntajeTotal;

    @Column(nullable = false, length = 50)
    private String categoriaUso;

    @Embedded
    private Audit audit = new Audit();
}