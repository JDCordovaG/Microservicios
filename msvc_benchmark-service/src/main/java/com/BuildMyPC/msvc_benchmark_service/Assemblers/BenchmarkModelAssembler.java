package com.BuildMyPC.msvc_benchmark_service.Assemblers;

import com.BuildMyPC.msvc_benchmark_service.Controllers.BenchmarkControllerV2;
import com.BuildMyPC.msvc_benchmark_service.Models.Benchmark;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BenchmarkModelAssembler implements RepresentationModelAssembler<Benchmark, EntityModel<Benchmark>> {

    @Override
    public EntityModel<Benchmark> toModel(Benchmark benchmark) {
        return EntityModel.of(
                benchmark,
                linkTo(methodOn(BenchmarkControllerV2.class).buscarPorId(benchmark.getId())).withSelfRel(),
                linkTo(methodOn(BenchmarkControllerV2.class).listarPorBuild(benchmark.getBuildId())).withRel("historial-build")
        );
    }
}