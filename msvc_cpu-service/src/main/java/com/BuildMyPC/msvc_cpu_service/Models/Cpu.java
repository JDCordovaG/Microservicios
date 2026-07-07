package com.BuildMyPC.msvc_cpu_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cpus")
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "componente_id", nullable = false)
    private Long componenteId;

    @Column(nullable = false)
    private String socket;

    private Integer nucleos;
    private Integer hilos;

    @Column(name = "frecuencia_base")
    private Double frecuenciaBase;

    @Column(name = "frecuencia_turbo")
    private Double frecuenciaTurbo;

    @Column(name = "tdp_watts", nullable = false)
    private Integer tdpWatts;

    private String generacion;

    @Column(name = "soporta_ddr4")
    private Boolean soportaDdr4;

    @Column(name = "soporta_ddr5")
    private Boolean soportaDdr5;

    private Boolean activo;

    @Embedded
    private Audit audit = new Audit();
}
