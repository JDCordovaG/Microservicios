package com.BuildMyPC.msvc_benchmark_service.Models.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GpuDTO {
    private Integer puntajeBase;
    private Integer vramGb;
}
