package com.agenda.consultorio.agendaconsultorio.mapper;

import com.agenda.consultorio.agendaconsultorio.dto.MedicoDTO;
import com.agenda.consultorio.agendaconsultorio.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicoMapper {

    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    Medico toModel(MedicoDTO medicoDTO);

    MedicoDTO toDTO(Medico consulta);
}
