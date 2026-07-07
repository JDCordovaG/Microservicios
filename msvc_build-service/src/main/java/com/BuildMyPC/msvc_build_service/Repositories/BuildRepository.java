package com.BuildMyPC.msvc_build_service.Repositories;

import com.BuildMyPC.msvc_build_service.Models.Build;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildRepository extends JpaRepository<Build, Long> {
    List<Build> findByEstado(String estado);

}
