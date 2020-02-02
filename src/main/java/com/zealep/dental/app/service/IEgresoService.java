package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Egreso;

import java.util.List;

public interface IEgresoService {

    Egreso findById(Long id);

    List<Egreso> findAll();

    List<Egreso> findAllActives();

    Egreso save(Egreso d);

    Egreso update(Egreso d);

    void deleteById(Long id);

    boolean isExist(Egreso d);
}
