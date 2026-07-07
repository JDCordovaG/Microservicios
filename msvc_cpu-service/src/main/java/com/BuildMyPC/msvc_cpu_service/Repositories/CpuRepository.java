package com.BuildMyPC.msvc_cpu_service.Repositories;

import com.BuildMyPC.msvc_cpu_service.Models.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, Long> {
    List<Cpu> findBySocketIgnoreCaseAndActivoTrue(String socket);
    List<Cpu> findByGeneracionIgnoreCaseAndActivoTrue(String generacion);
    List<Cpu> findByComponenteIdInAndActivoTrue(List<Long> componenteIds);
    List<Cpu> findByActivoTrue();
}
