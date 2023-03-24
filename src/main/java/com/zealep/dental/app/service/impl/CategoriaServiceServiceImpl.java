package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Categoria;
import com.zealep.dental.app.model.repository.CategoriaRepository;
import com.zealep.dental.app.service.ICategoriaService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoriaService")
public class CategoriaServiceServiceImpl implements ICategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> findAllActives() {
        return categoriaRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public Categoria save(Categoria c) {
        c.setEstado(Constantes.ESTADO_ACTIVO);
        return categoriaRepository.save(c);
    }

    @Override
    public Categoria update(Categoria c) {
        c.setEstado(Constantes.ESTADO_ACTIVO);
        return categoriaRepository.save(c);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Categoria c) {
        return findById(c.getIdCategoria())!=null;
    }
}
