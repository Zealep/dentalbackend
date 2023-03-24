package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Odontograma;
import com.zealep.dental.app.model.repository.OdontogramaRepository;
import com.zealep.dental.app.service.IOdontogramaService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("odontogramaService")
public class OdontogramaServiceImpl implements IOdontogramaService {

    @Autowired
    OdontogramaRepository odontogramaRepository;

    @Override
    @Transactional(readOnly = true)
    public Odontograma findById(Long id) {
        return odontogramaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Odontograma> findAll() {
        return odontogramaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Odontograma> findAllActives() {
        return odontogramaRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Odontograma> findByPaciente(Long id) {
        return odontogramaRepository.findByPaciente(id,Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Odontograma save(Odontograma i, MultipartFile file) {
        try {
            i.setImage(file.getBytes());
            i.setEstado(Constantes.ESTADO_ACTIVO);
            return odontogramaRepository.save(i);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Transactional
    public Odontograma update(Odontograma i) {
        return odontogramaRepository.save(i);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        odontogramaRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Odontograma i) {
        return findById(i.getIdOdontograma())!=null;
    }
}
