package com.agenda.consultorio.agendaconsultorio.service;

import com.agenda.consultorio.agendaconsultorio.dto.ConsultaDTO;
import com.agenda.consultorio.agendaconsultorio.dto.MessageResponseDTO;
import com.agenda.consultorio.agendaconsultorio.exception.*;
import com.agenda.consultorio.agendaconsultorio.mapper.ConsultaMapper;
import com.agenda.consultorio.agendaconsultorio.model.Consulta;
import com.agenda.consultorio.agendaconsultorio.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaMapper consultaMapper = ConsultaMapper.INSTANCE;

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<ConsultaDTO> listAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        List<Consulta> consultasOrdenadas = ordenarListaPorData(consultas);

        return consultasOrdenadas.stream()
                .map(consultaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultaDTO findById(Long id) throws ConsultaNotFoundException {
       Consulta consulta = verifyIfExists(id);
       return consultaMapper.toDTO(consulta);
    }

    public List<ConsultaDTO> listFilterMedic(Long idMedico){
        List<Consulta> consultas = consultaRepository.listFilterMedic(idMedico);
        List<Consulta> consultasOrdenadas = ordenarListaPorData(consultas);

        return consultasOrdenadas.stream()
                .map(consultaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO createConsulta(ConsultaDTO consultaDTO) throws ConsultaNotFoundException,
            ConsultaIntervaloException, ConsultaMedicosDiferentesException,
            ConsultaDataPacienteException, ConsultaLimiteExcepion {
        Consulta consultaParaSalvar = consultaMapper.toModel(consultaDTO);
        LocalDate dataParaSalvar = consultaParaSalvar.getDataHora().toLocalDate();
        Long idConsultorioParaSalvar = consultaParaSalvar.getConsultorio().getId();
        List<Consulta> consultas = consultaRepository.findAll();

        if(consultas.size() != 0){
            for(Consulta consulta : consultas){

                LocalDateTime dataHoraSalva = consulta.getDataHora().plus(15, ChronoUnit.MINUTES);
               if(consultaParaSalvar.getDataHora().equals(dataHoraSalva)
                       && !consultaParaSalvar.getMedico().getId().equals(consulta.getMedico().getId())
                       && !consultaParaSalvar.getEspecialidadeMedica().equals("Cirurgião")){
                   throw new ConsultaMedicosDiferentesException( );

               } else if(dataHoraSalva.isAfter(consultaParaSalvar.getDataHora())
                       && consulta.getConsultorio().getId() == consultaParaSalvar.getConsultorio().getId()
                       && !consultaParaSalvar.getEspecialidadeMedica().equals("Cirurgião")) {
                   throw new ConsultaIntervaloException( );
               }

               LocalDate dataAtual = consulta.getDataHora().toLocalDate();
               String pacienteAtual = consulta.getNomePaciente();
               if(dataParaSalvar.equals(dataAtual) && consultaParaSalvar.getNomePaciente().equals(pacienteAtual)){
                   throw new ConsultaDataPacienteException();
               }

            }

            List<Consulta> listaConsultaFiltradaPorData = consultas.stream()
                    .filter(consulta -> consulta.getDataHora().toLocalDate().equals(dataParaSalvar))
                    .collect(Collectors.toList());

            List<Consulta> listaConsultaFiltradaPorDataEConsultorio = listaConsultaFiltradaPorData.stream()
                    .filter(consulta -> consulta.getConsultorio().getId().equals(idConsultorioParaSalvar))
                    .collect(Collectors.toList());

            if(listaConsultaFiltradaPorDataEConsultorio.size() != 0) {
                if (listaConsultaFiltradaPorDataEConsultorio.size() == 12
                        && listaConsultaFiltradaPorDataEConsultorio.get(0).getConsultorio().getId().equals(idConsultorioParaSalvar)) {
                    throw new ConsultaLimiteExcepion();
                }

                if (!listaConsultaFiltradaPorDataEConsultorio.get(0).getConsultorio().getId()
                        .equals(idConsultorioParaSalvar)) {
                    throw new ConsultaNotFoundException();
                }
            }
        }

        Consulta consultaSalva = consultaRepository.save(consultaParaSalvar);
        return MessageResponseDTO
                .builder()
                .message("Consulta criada com ID: " + consultaSalva.getId())
                .build();
    }

    public MessageResponseDTO updateById(Long id, ConsultaDTO consultaDTO) throws ConsultaNotFoundException{
        verifyIfExists(id);
        Consulta consultaParaAlterar = consultaMapper.toModel(consultaDTO);
        Consulta consultaAtualizada = consultaRepository.save(consultaParaAlterar);
        return MessageResponseDTO
                .builder()
                .message("Consulta " + consultaAtualizada.getId() + " atualizada com sucesso!")
                .build();
    }

    public void delete(Long id) throws ConsultaNotFoundException{
        verifyIfExists(id);
        consultaRepository.deleteById(id);
    }

    private Consulta verifyIfExists(Long id) throws ConsultaNotFoundException{
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException(id));
    }

    private List<Consulta> ordenarListaPorData(List<Consulta> lista){
        Collections.sort(lista, new Comparator<Consulta>() {
            @Override
            public int compare(Consulta c1, Consulta c2) {
                return c2.getDataHora().compareTo(c1.getDataHora());
            }
        });
        return lista;
    }
}
