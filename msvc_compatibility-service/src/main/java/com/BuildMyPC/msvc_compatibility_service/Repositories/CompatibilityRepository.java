package com.BuildMyPC.msvc_compatibility_service.Repositories;

import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompatibilityRepository extends JpaRepository<ValidacionCompatibility, Long> {
    List<ValidacionCompatibility> findByCompatible(Boolean compatible);
    Optional<ValidacionCompatibility> findByBuildId(Long buildId); // Requerido por el PDF [cite: 237]
}
