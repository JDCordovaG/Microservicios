package com.BuildMyPC.msvc_component_service.Repositories;

import com.BuildMyPC.msvc_component_service.Models.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    List<Component> findByEstado(String estado);

}
