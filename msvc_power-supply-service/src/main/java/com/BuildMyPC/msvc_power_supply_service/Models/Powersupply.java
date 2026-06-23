package com.BuildMyPC.msvc_power_supply_service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fuentes_poder")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    @Column(nullable = false)
    private Boolean activo;

    @Embedded
    private Audit audit = new Audit();
}