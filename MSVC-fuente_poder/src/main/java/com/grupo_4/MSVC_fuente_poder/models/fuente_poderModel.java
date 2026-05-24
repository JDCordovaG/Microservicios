package com.grupo_4.MSVC_fuente_poder.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fuentes_poder")

public class fuente_poderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuente_poder_id")
    private Long id;

    @Column(nullable = false, name = "componente_id")
    private Long componenteId;

    @Column(nullable = false, name = "potencia_watts")
    private Integer potenciaWatts;

    @Column(nullable = false)
    private String certificacion;

    @Column(nullable = false)
    private String formato;

    @Column(nullable = false, name = "es_modular")
    private Boolean esModular;

    @Column(nullable = false)
    private String estado;
}
