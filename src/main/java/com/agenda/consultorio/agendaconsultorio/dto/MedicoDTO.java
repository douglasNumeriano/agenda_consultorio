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
public class MedicoDTO {

    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private Integer crm;

    private Integer idade;
}
