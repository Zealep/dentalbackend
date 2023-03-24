package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.repository.EgresoRepository;
import com.zealep.dental.app.service.IEgresoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("egresoService")
public class EgresoServiceImpl implements IEgresoService {

    @Autowired
    EgresoRepository egresoRepository;

    @Override
    @Transactional(readOnly = true)
    public Egreso findById(Long id) {
        return egresoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Egreso> findAll() {
        return (List<Egreso>) egresoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Egreso> findAllActives() {
        return egresoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Egreso save(Egreso d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return egresoRepository.save(d);
    }

    @Override
    @Transactional
    public Egreso update(Egreso d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return egresoRepository.save(d);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        egresoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Egreso d) {
        return findById(d.getIdEgreso())!=null;
    }

    @Override
    public Double findEgresosDia(LocalDate date) {
        Double result = egresoRepository.findTotalEgresosDia(date,Constantes.ESTADO_ACTIVO);
        return result == null ? 0 : result;
    }

    @Override
    public Double findEgresosMes() {
        Double result = egresoRepository.findTotalEgresosMes(Constantes.ESTADO_ACTIVO);
        return result == null ? 0 : result;
    }

    @Override
    public List<Egreso> findByRangeDates(LocalDate inicio, LocalDate fin) {
        return egresoRepository.findByRangeDates(inicio,fin,Constantes.ESTADO_ACTIVO);
    }
}
