package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Control;
import com.zealep.dental.app.model.repository.ControlRepository;
import com.zealep.dental.app.service.IControlService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("controlService")
public class ControlServiceImpl implements IControlService {

    @Autowired
    ControlRepository controlRepository;

    @Override
    @Transactional(readOnly = true)
    public Control findById(Long id) {
        return controlRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Control> findAll() {
        return (List<Control>) controlRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Control> findAllActives() {
        return controlRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Control> findByPaciente(Long idPaciente) {
        return controlRepository.findByPaciente(idPaciente,Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Control save(Control c) {
        c.setEstado(Constantes.ESTADO_ACTIVO);
        return controlRepository.save(c);
    }

    @Override
    @Transactional
    public Control update(Control c) {
        c.setEstado(Constantes.ESTADO_ACTIVO);
        return controlRepository.save(c);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        controlRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Control c) {
        return findById(c.getIdControl())!=null;
    }
}
