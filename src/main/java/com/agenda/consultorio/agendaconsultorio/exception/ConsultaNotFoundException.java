package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaNotFoundException  extends Exception{

    public ConsultaNotFoundException() {
        super("Não é permitido o cadastro desta consulta");
    }

    public ConsultaNotFoundException(Long id){
        super("Consulta not found with ID " + id);
    }

}
