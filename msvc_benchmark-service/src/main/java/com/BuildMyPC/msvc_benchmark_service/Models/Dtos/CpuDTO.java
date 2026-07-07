package com.BuildMyPC.msvc_benchmark_service.Models.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CpuDTO {
    private Integer nucleos;
    private Double frecuenciaBase;
    private Integer tdpWatts;
}
