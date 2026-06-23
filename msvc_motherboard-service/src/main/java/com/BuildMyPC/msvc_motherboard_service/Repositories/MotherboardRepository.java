package com.BuildMyPC.msvc_motherboard_service.Repositories;

import com.BuildMyPC.msvc_motherboard_service.Models.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    List<Motherboard> findByActivoTrue();
    Optional<Motherboard> findByIdAndActivoTrue(Long id);
    List<Motherboard> findBySocketIgnoreCaseAndActivoTrue(String socket);
    List<Motherboard> findByFormatoIgnoreCaseAndActivoTrue(String formato);
}