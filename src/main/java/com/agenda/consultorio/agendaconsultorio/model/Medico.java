package com.agenda.consultorio.agendaconsultorio.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Integer crm;

    @Column(nullable = true)
    private Integer idade;
}
