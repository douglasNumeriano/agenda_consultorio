package com.agenda.consultorio.agendaconsultorio.repository;

import com.agenda.consultorio.agendaconsultorio.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta AS c JOIN Medico AS m ON c.medico = m.id WHERE m.id = ?1")
    List<Consulta> listFilterMedic(Long idMedico);
}
