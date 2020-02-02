package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.model.repository.IngresoRepository;
import com.zealep.dental.app.service.IIngresoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IngresoServiceImpl implements IIngresoService {

    @Autowired
    IngresoRepository ingresoRepository;

    @Override
    public Ingreso findById(Long id) {
        return ingresoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ingreso> findAll() {
        return (List<Ingreso>) ingresoRepository.findAll();
    }

    @Override
    public List<Ingreso> findAllActives() {
        return ingresoRepository.findAllActives();
    }

    @Override
    public Ingreso save(Ingreso d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return ingresoRepository.save(d);
    }

    @Override
    public Ingreso update(Ingreso d) {
        return ingresoRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
        ingresoRepository.deleteLogicById(Constantes.ESTADO_ACTIVO,id);
    }

    @Override
    public boolean isExist(Ingreso d) {
        return findById(d.getIdIngreso())!=null;
    }
}
