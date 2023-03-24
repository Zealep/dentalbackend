package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.model.repository.IngresoRepository;
import com.zealep.dental.app.service.IIngresoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("ingresoService")
public class IngresoServiceImpl implements IIngresoService {

    @Autowired
    IngresoRepository ingresoRepository;

    @Override
    @Transactional(readOnly = true)
    public Ingreso findById(Long id) {
        return ingresoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> findAll() {
        return (List<Ingreso>) ingresoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> findAllActives() {
        return ingresoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Ingreso save(Ingreso d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return ingresoRepository.save(d);
    }

    @Override
    @Transactional
    public Ingreso update(Ingreso d) {
        return ingresoRepository.save(d);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ingresoRepository.deleteLogicById(Constantes.ESTADO_ACTIVO,id);
    }

    @Override
    public boolean isExist(Ingreso d) {
        return findById(d.getIdIngreso())!=null;
    }

    @Override
    public Double findIngresosDia(LocalDate date) {
        Double result = ingresoRepository.findTotalIngresosDia(date,Constantes.ESTADO_ACTIVO);
        return result == null ? 0 : result;
    }

    @Override
    public Double findIngresosMes() {
        Double result = ingresoRepository.findTotalIngresosMes(Constantes.ESTADO_ACTIVO);
        return result == null ? 0 : result;
    }

    @Override
    public List<Ingreso> findByRangeDates(LocalDate inicio, LocalDate fin) {
        return ingresoRepository.findByRangeDates(inicio,fin,Constantes.ESTADO_ACTIVO);
    }
}
