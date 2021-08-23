package com.agenda.consultorio.agendaconsultorio.controller;

import com.agenda.consultorio.agendaconsultorio.dto.MedicoDTO;
import com.agenda.consultorio.agendaconsultorio.dto.MessageResponseDTO;
import com.agenda.consultorio.agendaconsultorio.exception.MedicoNotFoundException;
import com.agenda.consultorio.agendaconsultorio.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<MedicoDTO> listAll(){
        return medicoService.listAll();
    }

    @GetMapping("/{id}")
    public MedicoDTO findById(@PathVariable Long id) throws MedicoNotFoundException {
        return medicoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createConsuta(@RequestBody @Valid MedicoDTO consultaDTO) throws MedicoNotFoundException{
        return medicoService.createConsulta(consultaDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id,
                                         @RequestBody @Valid MedicoDTO consultaDTO)
            throws  MedicoNotFoundException {
        return medicoService.updateById(id, consultaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws MedicoNotFoundException {
        medicoService.delete(id);
    }

}
