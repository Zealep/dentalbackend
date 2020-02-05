package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Tratamiento;

import java.util.List;

public interface ITratamientoService {

    Tratamiento findById(Long id);

    List<Tratamiento> findAll();

    List<Tratamiento> findAllActives();

    Tratamiento save(Tratamiento t);

    Tratamiento update(Tratamiento t);

    void deleteById(Long id);

    boolean isExist(Tratamiento t);
}
