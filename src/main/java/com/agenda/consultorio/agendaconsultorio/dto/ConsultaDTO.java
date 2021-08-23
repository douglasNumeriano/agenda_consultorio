package com.agenda.consultorio.agendaconsultorio.dto;

import com.agenda.consultorio.agendaconsultorio.model.Consultorio;
import com.agenda.consultorio.agendaconsultorio.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {

    private Long id;

    @NotEmpty
    private String nomePaciente;

    @NotEmpty
    private String especialidadeMedica;

    @NotEmpty
    private String dataHora;

    @Valid
    @NotEmpty
    private Consultorio consultorio;

    @Valid
    @NotEmpty
    private Medico medico;
}
