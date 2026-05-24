package com.grupo_4.MSVC_GPU.repositories;

import com.grupo_4.MSVC_GPU.models.GpuModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpuRepository extends JpaRepository<GpuModelo, Long>{
    List<GpuModelo> findByEstado(String estado);
}
