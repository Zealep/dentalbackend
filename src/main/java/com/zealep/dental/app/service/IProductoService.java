package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Producto;

import java.util.List;

public interface IProductoService {

    Producto findById(Long id);

    List<Producto> findAll();

    List<Producto> findAllActives();

    Producto save(Producto p);

    Producto update(Producto p);

    void deleteById(Long id);

    boolean isExist(Producto p);
}
