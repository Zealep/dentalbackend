package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Procedimiento;
import com.zealep.dental.app.model.repository.ProcedimientoRepository;
import com.zealep.dental.app.service.IProcedimientoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("procedimientoService")
public class ProcedimientoServiceImpl implements IProcedimientoService {

    @Autowired
    ProcedimientoRepository procedimientoRepository;

    @Override
    @Transactional(readOnly = true)
    public Procedimiento findById(Long id) {
        return procedimientoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Procedimiento> findAll() {
        return (List<Procedimiento>) procedimientoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Procedimiento> findAllActives() {
        return procedimientoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Procedimiento save(Procedimiento d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return procedimientoRepository.save(d);
    }

    @Override
    @Transactional
    public Procedimiento update(Procedimiento d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return procedimientoRepository.save(d);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        procedimientoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Procedimiento d) {
        return findById(d.getIdProcedimiento())!=null;
    }
}
