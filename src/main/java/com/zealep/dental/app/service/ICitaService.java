package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Cita;

import java.util.List;

public interface ICitaService {

    Cita findById(Long id);

    List<Cita> findAll();

    List<Cita> findAllActives();

    Cita save(Cita c);

    Cita update(Cita c);

    void deleteById(Long id);

    boolean isExist(Cita c);
}
