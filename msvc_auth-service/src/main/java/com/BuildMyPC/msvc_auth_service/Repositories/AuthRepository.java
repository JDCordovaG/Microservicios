package com.BuildMyPC.msvc_auth_service.Repositories;

import com.BuildMyPC.msvc_auth_service.Models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    List<Auth> findByEstado(String estado);

}
