package com.BuildMyPC.msvc_component_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;

    @Column(nullable = false, name = "tipo_component")
    private String tipo;

    @Column(nullable = false, name = "marca_component")
    private String marca;

    @Column(nullable = false, name = "modelo_component")
    private String modelo;

    @Column(nullable = false, name = "precio_base_component")
    private Integer precioBase;

    @Column(nullable = false, name = "estado_component")
    private String estado;

    @Column(nullable = false, name = "descripcion_component")
    private String descripcion;

    @Column(nullable = false, name = "fecha_lanzamiento_component")
    private LocalDate fechaLanzamiento;

}
