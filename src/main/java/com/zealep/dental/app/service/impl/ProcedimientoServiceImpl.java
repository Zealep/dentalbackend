package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Procedimiento;
import com.zealep.dental.app.model.repository.ProcedimientoRepository;
import com.zealep.dental.app.service.IProcedimientoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProcedimientoServiceImpl implements IProcedimientoService {

    @Autowired
    ProcedimientoRepository procedimientoRepository;

    @Override
    public Procedimiento findById(Long id) {
        return procedimientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Procedimiento> findAll() {
        return (List<Procedimiento>) procedimientoRepository.findAll();
    }

    @Override
    public List<Procedimiento> findAllActives() {
        return procedimientoRepository.findAllActives();
    }

    @Override
    public Procedimiento save(Procedimiento d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return procedimientoRepository.save(d);
    }

    @Override
    public Procedimiento update(Procedimiento d) {
        return procedimientoRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
        procedimientoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Procedimiento d) {
        return findById(d.getIdProcedimiento())!=null;
    }
}
