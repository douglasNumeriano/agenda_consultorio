package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaMedicosDiferentesException extends Exception{

    public ConsultaMedicosDiferentesException(){
        super("Cadastro de consulta no mesmo consultório, médicos diferenteres e mesmo horário, permitido somente para médicos Cirurgiões");
    }
}
