package com.grupo_4.MSVC_placa_madre.repositories;

import com.grupo_4.MSVC_placa_madre.models.Placa_madreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Placa_madreRepository extends JpaRepository<Placa_madreModel, Long> {
    List<Placa_madreModel> findByEstado(String estado);
}
