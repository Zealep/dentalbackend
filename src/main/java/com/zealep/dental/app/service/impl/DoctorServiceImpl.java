package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Doctor;
import com.zealep.dental.app.model.repository.DoctorRepository;
import com.zealep.dental.app.service.IDoctorService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    @Transactional(readOnly = true)
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAllActives() {
        return doctorRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Doctor save(Doctor d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return doctorRepository.save(d);
    }

    @Override
    @Transactional
    public Doctor update(Doctor d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return doctorRepository.save(d);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
doctorRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Doctor d) {
        return findById(d.getIdDoctor())!=null;
    }
}
