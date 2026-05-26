package com.BuildMyPC.msvc_benchmark_service.Repositories;

import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {
    List<Benchmark> findByBuildId(Long buildId);

}
