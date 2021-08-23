package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaDataPacienteException extends Exception{

    public ConsultaDataPacienteException(){
        super("Não é permitido marcar duas consultas do mesmo paciente no mesmo dia");
    }
}
