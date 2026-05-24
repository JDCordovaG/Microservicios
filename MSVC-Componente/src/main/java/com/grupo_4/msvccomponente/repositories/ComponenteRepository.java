package com.grupo_4.msvccomponente.repositories;

import com.grupo_4.msvccomponente.models.ComponenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponenteRepository extends JpaRepository<ComponenteModel, Long> {
    List<ComponenteModel> findByTipo(String tipo);
    List<ComponenteModel> findByMarca(String marca);
    List<ComponenteModel> findByEstado(String estado);
}
