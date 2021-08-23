package com.agenda.consultorio.agendaconsultorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicoNotFoundException extends Exception {

    public MedicoNotFoundException(Long id){

        super("Médico not found with ID " + id);
    }
}
