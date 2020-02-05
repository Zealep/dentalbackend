package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.model.repository.ImagenRepository;
import com.zealep.dental.app.service.IImagenService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("imagenService")
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    @Override
    @Transactional(readOnly = true)
    public Imagen findById(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> findAll() {
        return (List<Imagen>) imagenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> findAllActives() {
        return imagenRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Imagen save(Imagen i) {
        i.setEstado(Constantes.ESTADO_ACTIVO);
        i.getArchivos().forEach(x -> x.setImagen(i));
        return imagenRepository.save(i);
    }


    @Override
    @Transactional
    public Imagen update(Imagen i) {
        i.getArchivos().forEach(x -> x.setImagen(i));
        return imagenRepository.save(i);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        imagenRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Imagen i) {
        return false;
    }
}
