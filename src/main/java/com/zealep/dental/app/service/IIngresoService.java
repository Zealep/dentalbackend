package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Ingreso;

import java.util.List;

public interface IIngresoService {

    Ingreso findById(Long id);

    List<Ingreso> findAll();

    List<Ingreso> findAllActives();

    Ingreso save(Ingreso d);

    Ingreso update(Ingreso d);

    void deleteById(Long id);

    boolean isExist(Ingreso d);
}
