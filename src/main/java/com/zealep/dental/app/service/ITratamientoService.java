package com.zealep.dental.app.service;

import com.zealep.dental.app.model.dto.PlanTratamientoDTO;
import com.zealep.dental.app.model.dto.TratamientoPagarDTO;
import com.zealep.dental.app.model.entities.Tratamiento;

import java.util.List;

public interface ITratamientoService {

    Tratamiento findById(Long id);

    List<Tratamiento> findAll();

    List<Tratamiento> findAllActives();

    List<TratamientoPagarDTO> findTratamientoPagarByPaciente(Long idPaciente);

    Tratamiento save(Tratamiento t);

    Tratamiento update(Tratamiento t);

    void deleteById(Long id);

    boolean isExist(Tratamiento t);

    boolean savePlanTratamiento(PlanTratamientoDTO planTratamientoDTO);

    boolean changeEtapa(String etapa,Long id);

    List<Tratamiento> findByPaciente(Long id);

    List<Tratamiento> findByPacienteAndEtapa(Long id,String etapa);

    byte[] generarContrato(Tratamiento t);

    List<Tratamiento> findNewsTratamientos();
}
