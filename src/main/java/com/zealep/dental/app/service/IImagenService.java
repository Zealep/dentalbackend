package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImagenService {

    Imagen findById(Long id);

    List<Imagen> findAll();

    List<Imagen> findAllActives();

    List<Imagen> findByPaciente(Long id);

    Imagen save(Imagen i, MultipartFile file);

    Imagen update(Imagen i);

    void deleteById(Long id);

    boolean isExist(Imagen i);
}
