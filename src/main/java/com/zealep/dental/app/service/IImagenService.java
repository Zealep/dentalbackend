package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Imagen;

import java.util.List;

public interface IImagenService {

    Imagen findById(Long id);

    List<Imagen> findAll();

    List<Imagen> findAllActives();

    Imagen save(Imagen i);

    Imagen update(Imagen i);

    void deleteById(Long id);

    boolean isExist(Imagen i);
}
