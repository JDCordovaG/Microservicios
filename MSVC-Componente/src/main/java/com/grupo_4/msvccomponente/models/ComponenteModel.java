package com.grupo_4.msvccomponente.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "componentes")
public class ComponenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "componente_id")
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, name = "precio_base")
    private Double precioBase;

    @Column(nullable = false)
    private String estado;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

}
