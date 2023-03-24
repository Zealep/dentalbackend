package com.zealep.dental.app.service;

import com.zealep.dental.app.model.dto.CitaDTO;
import com.zealep.dental.app.model.entities.Cita;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICitaService {

    Cita findById(Long id);

    List<Cita> findAll();

    List<Cita> findAllActives();

    List<Cita> findByPaciente(Long id);

    List<CitaDTO> findByDate(LocalDate fecha);

    Cita save(Cita c);

    Cita update(Cita c);

    void deleteById(Long id);

    boolean isExist(Cita c);

    void changeEtapa(Long id,String etapa);
}
