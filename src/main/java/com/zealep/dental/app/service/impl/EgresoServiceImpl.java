package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.repository.EgresoRepository;
import com.zealep.dental.app.service.IEgresoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EgresoServiceImpl implements IEgresoService {

    @Autowired
    EgresoRepository egresoRepository;

    @Override
    public Egreso findById(Long id) {
        return egresoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Egreso> findAll() {
        return (List<Egreso>) egresoRepository.findAll();
    }

    @Override
    public List<Egreso> findAllActives() {
        return egresoRepository.findAllActives();
    }

    @Override
    public Egreso save(Egreso d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return egresoRepository.save(d);
    }

    @Override
    public Egreso update(Egreso d) {
        return egresoRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
        egresoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Egreso d) {
        return findById(d.getIdEgreso())!=null;
    }
}
