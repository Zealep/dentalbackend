package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Pago;
import com.zealep.dental.app.model.repository.PagoRepository;
import com.zealep.dental.app.service.IPagoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("pagoService")
public class PagoServiceImpl implements IPagoService {

    @Autowired
    PagoRepository pagoRepository;

    @Override
    @Transactional(readOnly = true)
    public Pago findById(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> findAll() {
        return (List<Pago>) pagoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> findAllActives() {
        return pagoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Pago save(Pago p) {
        p.setEstado(Constantes.ESTADO_ACTIVO);
        return pagoRepository.save(p);
    }

    @Override
    @Transactional
    public Pago update(Pago p) {
        return pagoRepository.save(p);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Pago p) {
        return findById(p.getIdPago())!=null;
    }
}
