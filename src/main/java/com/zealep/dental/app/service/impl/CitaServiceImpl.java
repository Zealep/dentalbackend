package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Cita;
import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.model.repository.CitaRepository;
import com.zealep.dental.app.service.ICitaService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("citaService")
public class CitaServiceImpl implements ICitaService {

    @Autowired
    CitaRepository citaRepository;


    @Override
    @Transactional(readOnly = true)
    public Cita findById(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findAll() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findAllActives() {
        return citaRepository.findAllActives(Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public Cita save(Cita c) {
        c.setEstado(Constantes.ESTADO_ACTIVO);
        return citaRepository.save(c);
    }

    @Override
    @Transactional
    public Cita update(Cita c) {
        return citaRepository.save(c);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Cita c) {
        return findById(c.getIdCita())!=null;
    }
}
