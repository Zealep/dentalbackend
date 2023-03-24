package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Ortodoncia;

import java.util.List;

public interface IOrtodonciaService {

    Ortodoncia findById(Long id);

    Ortodoncia findByTratamiento(Long id);

    List<Ortodoncia> findAll();

    List<Ortodoncia> findAllActives();

    Ortodoncia save(Ortodoncia o);

    Ortodoncia update(Ortodoncia o);

    void deleteById(Long id);

    boolean isExist(Ortodoncia o);

    List<Ortodoncia> findNewsOrtodoncia();
}
