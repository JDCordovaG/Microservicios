package com.grupo_4.MSVC_CPU.models;

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
    @Column(name = "cpu_id")
    private Long id;

    @Column(nullable = false, name = "componente_id")
    private Long componenteId;

    @Column(nullable = false, name = "socket_cpu")
    private String socket;

    @Column(nullable = false, name = "nucleos_cpu")
    private Integer nucleos;

    @Column(nullable = false, name = "hilos_cpu")
    private Integer hilos;

    @Column(nullable = false, name = "frecuencia_base_cpu")
    private Double frecuenciaBase;

    @Column(nullable = false, name = "frecuencia_turbo_cpu")
    private Double frecuenciaTurbo;

    @Column(nullable = false, name = "tdp_watts_cpu")
    private Integer tdpWatts;

    @Column(nullable = false, name = "soporta_ddr4_cpu")
    private Boolean soportaDdr4;
    @Column(nullable = false, name = "soporta_ddr5_cpu")
    private Boolean soportaDdr5;

    @Column(nullable = false)
    private String estado;

}
