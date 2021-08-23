package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaIntervaloException extends Exception {
    public ConsultaIntervaloException(){
        super("Cadastro de consulta com intervalo menor que 15 minutos não é permitido.");
    }
}
