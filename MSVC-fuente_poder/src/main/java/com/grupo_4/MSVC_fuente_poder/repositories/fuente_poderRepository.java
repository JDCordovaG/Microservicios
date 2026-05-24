package com.grupo_4.MSVC_fuente_poder.repositories;

import com.grupo_4.MSVC_fuente_poder.models.fuente_poderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface fuente_poderRepository extends JpaRepository<fuente_poderModel, Long> {
    List<fuente_poderModel> findByEstado(String estado);
}
