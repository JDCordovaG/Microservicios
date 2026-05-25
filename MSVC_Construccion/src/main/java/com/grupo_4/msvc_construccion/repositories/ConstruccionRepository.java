package com.grupo_4.msvc_construccion.repositories;

import com.grupo_4.msvc_construccion.models.ConstruccionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstruccionRepository extends JpaRepository<ConstruccionModel, Long> {
    List<ConstruccionModel> findByUsuarioId(Long usuarioId);
    List<ConstruccionModel> findByEstado(String estado);
}
