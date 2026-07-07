package com.BuildMyPC.msvc_power_supply_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fuentes_poder")

public class Powersupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuente_poder_id")
    private Long id;

    @Column(nullable = false, name = "componente_id_fuentepoder")
    private Long componenteId;

    @Column(nullable = false, name = "potencia_watts_fuentepoder")
    private Integer potenciaWatts;

    @Column(nullable = false, name = "certificacion_fuentepoder")
    private String certificacion;

    @Column(nullable = false, name = "formato_fuentepoder")
    private String formato;

    @Column(nullable = false, name = "es_modular_fuentepoder")
    private Boolean esModular;

    @Column(nullable = false, name = "estado_fuentepoder")
    private String estado;
}