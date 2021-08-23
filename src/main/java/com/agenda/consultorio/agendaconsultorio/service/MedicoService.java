package com.agenda.consultorio.agendaconsultorio.service;

import com.agenda.consultorio.agendaconsultorio.dto.MedicoDTO;
import com.agenda.consultorio.agendaconsultorio.dto.MessageResponseDTO;
import com.agenda.consultorio.agendaconsultorio.exception.MedicoNotFoundException;
import com.agenda.consultorio.agendaconsultorio.mapper.MedicoMapper;
import com.agenda.consultorio.agendaconsultorio.model.Medico;
import com.agenda.consultorio.agendaconsultorio.repository.MedicoResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoMapper medicoMapper = MedicoMapper.INSTANCE;

    @Autowired
    private MedicoResitory medicoRepository;

    public List<MedicoDTO> listAll() {
        List<Medico> medicoList = medicoRepository.findAll();

        return medicoList.stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MedicoDTO findById(Long id) throws MedicoNotFoundException {
        Medico medico = verifyIfExists(id);
        return medicoMapper.toDTO(medico);
    }

    public MessageResponseDTO createConsulta(MedicoDTO medicoDTO) throws MedicoNotFoundException {
        Medico medicoParaSalvar = medicoMapper.toModel(medicoDTO);

        Medico medicoSalva = medicoRepository.save(medicoParaSalvar);
        return MessageResponseDTO
                .builder()
                .message("Médico criado com ID: " + medicoSalva.getId())
                .build();
    }

    public MessageResponseDTO updateById(Long id, MedicoDTO medicoDTO) throws MedicoNotFoundException{
        verifyIfExists(id);
        Medico medicoParaAlterar = medicoMapper.toModel(medicoDTO);
        Medico medicoAtualizado = medicoRepository.save(medicoParaAlterar);
        return MessageResponseDTO
                .builder()
                .message("Médico " + medicoAtualizado.getId() + " atualizado com sucesso!")
                .build();
    }

    public void delete(Long id) throws MedicoNotFoundException{
        verifyIfExists(id);
        medicoRepository.deleteById(id);
    }

    private Medico verifyIfExists(Long id) throws MedicoNotFoundException{
        return medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
    }
}
