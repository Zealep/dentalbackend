package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Doctor;
import com.zealep.dental.app.model.repository.DoctorRepository;
import com.zealep.dental.app.service.IDoctorService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findAllActives() {
        return findAllActives();
    }

    @Override
    public Doctor save(Doctor d) {
        d.setEstado(Constantes.ESTADO_ACTIVO);
        return doctorRepository.save(d);
    }

    @Override
    public Doctor update(Doctor d) {
        return doctorRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
doctorRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Doctor d) {
        return findById(d.getIdDoctor())!=null;
    }
}
