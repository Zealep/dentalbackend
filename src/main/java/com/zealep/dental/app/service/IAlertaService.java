package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Alerta;

import java.util.List;

public interface IAlertaService {
    
    Alerta findById(Long id);

    List<Alerta> findAll();

    List<Alerta> findAllActives();

    Alerta save(Alerta d);

    Alerta update(Alerta d);

    void deleteById(Long id);

    boolean isExist(Alerta d);
}
