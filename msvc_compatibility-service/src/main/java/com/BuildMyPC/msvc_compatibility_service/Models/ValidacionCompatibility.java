package com.BuildMyPC.msvc_compatibility_service.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "validacion_compatibilidades")
public class ValidacionCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "validacion_id")
    private Long id;

    @Column(nullable = false, name = "build_id")
    private Long buildId;

    @Column(nullable = false)
    private Boolean compatible; // Mejorado a Boolean para facilitar la lógica

    @Column(nullable = false, name = "consumo_estimado_watts")
    private Integer consumoEstimadoWatts;

    @Column(nullable = false, name = "margen_fuente")
    private String margenFuente;

    @Column(nullable = false, length = 500)
    private String observaciones;

    @Column(nullable = false, name = "fecha_validacion", updatable = false)
    private LocalDateTime fechaValidacion;

    @OneToMany(mappedBy = "validacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleCompatibility> detalles = new ArrayList<>();

    public void addDetalle(DetalleCompatibility detalle) {
        detalles.add(detalle);
        detalle.setValidacion(this);
    }

    // Se elimina la asignación manual de fechaCalculo y se delega a la auditoría embebida
    @Embedded
    private Audit audit = new Audit();
}