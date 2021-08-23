package com.agenda.consultorio.agendaconsultorio.controller;

import com.agenda.consultorio.agendaconsultorio.dto.ConsultaDTO;
import com.agenda.consultorio.agendaconsultorio.dto.MessageResponseDTO;
import com.agenda.consultorio.agendaconsultorio.exception.*;
import com.agenda.consultorio.agendaconsultorio.model.Medico;
import com.agenda.consultorio.agendaconsultorio.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<ConsultaDTO> listAll(){
        return consultaService.listAll();
    }

    @GetMapping("/{id}")
    public ConsultaDTO findById(@PathVariable Long id) throws ConsultaNotFoundException {
        return consultaService.findById(id);
    }

    @GetMapping("/filtro/{medico}")
    public List<ConsultaDTO> listFilterMedic(@PathVariable Medico medico){
        return consultaService.listFilterMedic(medico.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createConsuta(@RequestBody @Valid ConsultaDTO consultaDTO) throws ConsultaNotFoundException,
            ConsultaIntervaloException, ConsultaMedicosDiferentesException,
            ConsultaDataPacienteException, ConsultaLimiteExcepion {
        return consultaService.createConsulta(consultaDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id,
                                         @RequestBody @Valid ConsultaDTO consultaDTO)
                                        throws  ConsultaNotFoundException {
        return consultaService.updateById(id, consultaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ConsultaNotFoundException{
        consultaService.delete(id);
    }
}
