package com.agenda.consultorio.agendaconsultorio.mapper;

import com.agenda.consultorio.agendaconsultorio.dto.ConsultaDTO;
import com.agenda.consultorio.agendaconsultorio.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultaMapper {

    ConsultaMapper INSTANCE = Mappers.getMapper(ConsultaMapper.class);

    @Mapping(target = "dataHora", source = "dataHora", dateFormat = "dd/MM/yyyy HH:mm:ss")
    Consulta toModel(ConsultaDTO consultaDTO);

    ConsultaDTO toDTO(Consulta consulta);
}
