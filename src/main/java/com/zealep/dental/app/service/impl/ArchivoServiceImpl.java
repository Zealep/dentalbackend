package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Archivo;
import com.zealep.dental.app.model.repository.ArchivoRepository;
import com.zealep.dental.app.service.IArchivoService;
import com.zealep.dental.app.util.Constantes;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("archivoService")
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    ArchivoRepository archivoRepository;

    @Override
    @Transactional(readOnly = true)
    public Archivo findById(Long id) {
        return archivoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Archivo findByImagen(Long id) {
        return archivoRepository.findByImagen(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Archivo> findAll() {
        return (List<Archivo>) archivoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Archivo> findAllActives() {
        return archivoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Archivo save(Archivo a) {
        a.setEstado(Constantes.ESTADO_ACTIVO);
        return archivoRepository.save(a);
    }

    @Override
    @Transactional
    public Archivo update(Archivo a) {
        a.setEstado(Constantes.ESTADO_ACTIVO);
        return archivoRepository.save(a);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        archivoRepository.deleteLogicById(Constantes.ESTADO_INACTIVO,id);
    }

    @Override
    public boolean isExist(Archivo a) {
        return findById(a.getIdArchivo())!=null;
    }

    @Override
    public byte[] obtenerImagen(String path) {
        byte[] img;
        try {
            img = FileUtils.readFileToByteArray(new File(FilenameUtils.separatorsToSystem(path)));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return img;
    }

    @Override
    public String uploadImagen(MultipartFile file, Long id) {
        return null;
    }
}
