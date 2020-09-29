package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Producto;
import com.zealep.dental.app.model.repository.ProductoRepository;
import com.zealep.dental.app.service.IProductoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productoService")
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public List<Producto> findAllActives() {
        return productoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public Producto save(Producto p) {
        p.setEstado(Constantes.ESTADO_ACTIVO);
        return productoRepository.save(p);
    }

    @Override
    public Producto update(Producto p) {
        p.setEstado(Constantes.ESTADO_ACTIVO);
        return productoRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Producto p) {
        return findById(p.getIdProducto())!=null;
    }
}
