package com.BuildMyPC.msvc_power_supply_service.Repositories;

import com.BuildMyPC.msvc_power_supply_service.Models.Powersupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowersupplyRepository extends JpaRepository<Powersupply, Long> {
    List<Powersupply> findByEstado(String estado);
}