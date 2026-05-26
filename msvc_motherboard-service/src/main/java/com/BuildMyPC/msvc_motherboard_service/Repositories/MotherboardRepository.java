package com.BuildMyPC.msvc_motherboard_service.Repositories;

import com.BuildMyPC.msvc_motherboard_service.Models.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    List<Motherboard> findByEstado(String estado);
}
