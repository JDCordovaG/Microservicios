package com.BuildMyPC.msvc_benchmark_service.Models.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BuildDTO {
    private Long buildId;
    private String estado;
    private Long gpuId;
    private Long cpuId;
}