package com.grupo_4.MSVC_placa_madre.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "placas madre")

public class Placa_madreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placamadre_id")
    private Long id;

    @Column(nullable = false, name = "componente_id")
    private Long componenteId;

    @Column(nullable = false)
    private String socket;

    @Column(nullable = false)
    private String chipset;

    @Column(nullable = false, name = "tipo_ram_soportada")
    private String tipoRamSoportada;

    @Column(nullable = false, name = "slots_ram")
    private Integer slotsRam;

    @Column(nullable = false, name = "max_ram_gb")
    private Integer maxRamGb;

    @Column(nullable = false)
    private String formato;

    @Column(nullable = false)
    private String estado;
}
