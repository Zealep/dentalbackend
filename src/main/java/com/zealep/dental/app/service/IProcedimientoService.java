package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Procedimiento;

import java.util.List;

public interface IProcedimientoService {

    Procedimiento findById(Long id);

    List<Procedimiento> findAll();

    List<Procedimiento> findAllActives();

    Procedimiento save(Procedimiento d);

    Procedimiento update(Procedimiento d);

    void deleteById(Long id);

    boolean isExist(Procedimiento d);
}
