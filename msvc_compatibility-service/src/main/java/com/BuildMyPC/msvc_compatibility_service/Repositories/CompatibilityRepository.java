package com.BuildMyPC.msvc_compatibility_service.Repositories;

import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompatibilityRepository extends JpaRepository<ValidacionCompatibility, Long> {
    List<ValidacionCompatibility> findByCompatible(String compatible);

}
