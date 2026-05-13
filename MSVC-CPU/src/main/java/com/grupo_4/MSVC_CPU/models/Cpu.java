package com.grupo_4.MSVC_CPU.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cpu")
@Getter
@Setter
@ToString
@NoArgsConstructor


public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpu_id")
    private Long cpuId;

    @Column(nullable = false, name = "socket_cpu")
    @NotBlank(message = "El campo socket no puede estar vacio")
    private Long marca;

    @Column(nullable = false, name = "generacion_cpu")
    @NotBlank(message = "El campo generacion ghz no puede estar vacio")
    private Long generacion;

    @Column(nullable = false, name = "nucleos_cpu")
    @NotBlank(message = "El campo nucleos no puede estar vacio")
    private Long nucleos;

    @Column(nullable = false, name = "hilos_cpu")
    @NotBlank(message = "El campo hilos no puede estar vacio")
    private Long hilos;

    @Column(nullable = false, name = "frecuencia_base_cpu")
    @NotBlank(message = "El campo frecuencia base no puede estar vacio")
    private Long frecuenciaBase;

    @Column(nullable = false, name = "frecuencia_turbo_cpu")
    @NotBlank(message = "El campo frecuencia turbo no puede estar vacio")
    private Long frecuenciaTurbo;

    @Column(nullable = false, name = "tdp_watts_cpu")
    @NotBlank(message = "El campo tdp watts no puede estar vacio")
    private Long tdpWatts;

    @Column(nullable = false, name = "soporta_ddr4_cpu")
    @NotBlank(message = "El campo soporta ddr4 no puede estar vacio")
    private Long soportaDdr4;

    @Column(nullable = false, name = "soporta_ddr5_cpu")
    @NotBlank(message = "El campo generacion ghz no puede estar vacio")
    private Long soportaDdr5;







}
