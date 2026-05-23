package com.grupo_4.MSVC_CPU.repositories;

import com.grupo_4.MSVC_CPU.models.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CpuRepository extends JpaRepository<Cpu, Long> {
    List<Cpu> findByEstado(String estado);

}
