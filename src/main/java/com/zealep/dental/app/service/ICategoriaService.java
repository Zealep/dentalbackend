package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Categoria;

import java.util.List;

public interface ICategoriaService {

    Categoria findById(Long id);

    List<Categoria> findAll();

    List<Categoria> findAllActives();

    Categoria save(Categoria c);

    Categoria update(Categoria c);

    void deleteById(Long id);

    boolean isExist(Categoria c);
}
