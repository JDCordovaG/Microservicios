package com.BuildMyPC.msvc_gpu_service.Repositories;

import com.BuildMyPC.msvc_gpu_service.Models.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GpuRepository extends JpaRepository<Gpu, Long> {
    List<Gpu> findByActivoTrue();
    Optional<Gpu> findByIdAndActivoTrue(Long id);
    List<Gpu> findByFabricanteChipIgnoreCaseAndActivoTrue(String fabricanteChip);
}
