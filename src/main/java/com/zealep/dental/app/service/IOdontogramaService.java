package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Odontograma;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOdontogramaService {

    Odontograma findById(Long id);

    List<Odontograma> findAll();

    List<Odontograma> findAllActives();

    List<Odontograma> findByPaciente(Long id);

    Odontograma save(Odontograma i, MultipartFile file);

    Odontograma update(Odontograma i);

    void deleteById(Long id);

    boolean isExist(Odontograma i);
}
