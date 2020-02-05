package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Tratamiento;
import com.zealep.dental.app.model.repository.TratamientoRepository;
import com.zealep.dental.app.service.ITratamientoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tratamientoService")
public class TratamientoServiceImpl implements ITratamientoService {

    @Autowired
    TratamientoRepository tratamientoRepository;

    @Override
    @Transactional(readOnly = true)
    public Tratamiento findById(Long id) {
        return tratamientoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findAll() {
        return (List<Tratamiento>) tratamientoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findAllActives() {
        return tratamientoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Tratamiento save(Tratamiento t) {
        t.getTratamientoDetalles().forEach(x -> x.setTratamiento(t));
        return tratamientoRepository.save(t);
    }

    @Override
    @Transactional
    public Tratamiento update(Tratamiento t) {
        t.setEstado(Constantes.ESTADO_ACTIVO);
        t.getTratamientoDetalles().forEach(x -> x.setTratamiento(t));
        return tratamientoRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tratamientoRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Tratamiento t) {
        return findById(t.getIdTratamiento())!=null;
    }
}
