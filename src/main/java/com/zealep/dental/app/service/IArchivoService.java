package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IArchivoService {
    
    Archivo findById(Long id);

    Archivo findByImagen(Long id);

    List<Archivo> findAll();

    List<Archivo> findAllActives();

    Archivo save(Archivo a);

    Archivo update(Archivo a);

    void deleteById(Long id);

    boolean isExist(Archivo a);

    byte[] obtenerImagen(String path);

    String uploadImagen(MultipartFile file, Long id);
}
