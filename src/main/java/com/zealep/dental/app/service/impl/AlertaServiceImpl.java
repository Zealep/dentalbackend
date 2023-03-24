package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Alerta;
import com.zealep.dental.app.model.repository.AlertaRepository;
import com.zealep.dental.app.service.IAlertaService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alertaService")
public class AlertaServiceImpl implements IAlertaService {

    @Autowired
    AlertaRepository alertaRepository;

    @Override
    public Alerta findById(Long id) {
        return alertaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alerta> findAll() {
        return (List<Alerta>) alertaRepository.findAll();
    }

    @Override
    public List<Alerta> findAllActives() {
        return alertaRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public Alerta save(Alerta d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return alertaRepository.save(d);
    }

    @Override
    public Alerta update(Alerta d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return alertaRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
            alertaRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Alerta d) {
        return findById(d.getIdAlerta())!=null;
    }
}
