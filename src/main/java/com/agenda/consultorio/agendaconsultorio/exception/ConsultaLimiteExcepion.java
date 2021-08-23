package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaLimiteExcepion extends Exception{

    public ConsultaLimiteExcepion(){
        super("Limite de 12 consultas por consultório por dia");
    }
}
