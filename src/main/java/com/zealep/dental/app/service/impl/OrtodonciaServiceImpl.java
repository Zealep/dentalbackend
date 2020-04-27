package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Ortodoncia;
import com.zealep.dental.app.model.repository.OrtodonciaRepository;
import com.zealep.dental.app.service.IOrtodonciaService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ortodonciaService")
public class OrtodonciaServiceImpl implements IOrtodonciaService {

    @Autowired
    OrtodonciaRepository ortodonciaRepository;

    @Override
    @Transactional(readOnly = true)
    public Ortodoncia findById(Long id) {
        return ortodonciaRepository.findById(id).orElse(null);
    }

    @Override
    public Ortodoncia findByTratamiento(Long id) {
        return ortodonciaRepository.findByTratamiento(id,Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ortodoncia> findAll() {
        return (List<Ortodoncia>) ortodonciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ortodoncia> findAllActives() {
        return ortodonciaRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Ortodoncia save(Ortodoncia o) {
        o.setEstado(Constantes.ESTADO_ACTIVO);
        return ortodonciaRepository.save(o);
    }

    @Override
    @Transactional
    public Ortodoncia update(Ortodoncia o) {
        return ortodonciaRepository.save(o);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ortodonciaRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Ortodoncia o) {
        return findById(o.getIdOrtodoncia())!=null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ortodoncia> findNewsOrtodoncia() {
        return ortodonciaRepository.findNewsOrtodoncias();
    }
}
