package com.BuildMyPC.msvc_gpu_service.Models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gpus")
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpu_id")
    private Long id;

    @Column(nullable = false, name = "componente_id")
    private Long componenteId;

    @Column(nullable = false, name = "vram_gb")
    private Integer vramGb;

    @Column(nullable = false, name = "tipo_memoria")
    private String tipoMemoria;

    @Column(nullable = false, name = "tdp_watts")
    private Integer tdpWatts;

    @Column(nullable = false, name = "largo_mm")
    private Double largoMm;

    @Column(nullable = false, name = "puntaje_base")
    private Integer puntajeBase;

    @Column(nullable = false, name = "fabricante_chip")
    private String fabricanteChip;

    @Column(nullable = false)
    private String estado;
}