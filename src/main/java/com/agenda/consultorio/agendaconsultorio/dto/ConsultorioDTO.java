package com.agenda.consultorio.agendaconsultorio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultorioDTO {

    private Long id;

    @NotEmpty
    private Integer numero;
}
