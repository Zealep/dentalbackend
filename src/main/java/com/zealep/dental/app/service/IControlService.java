package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Control;

import java.util.List;

public interface IControlService {


    Control findById(Long id);

    List<Control> findAll();

    List<Control> findAllActives();

    List<Control> findByPaciente(Long idPaciene);

    Control save(Control c);

    Control update(Control c);

    void deleteById(Long id);

    boolean isExist(Control c);
}
