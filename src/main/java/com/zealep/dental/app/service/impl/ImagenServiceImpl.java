package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Archivo;
import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.model.repository.ArchivoRepository;
import com.zealep.dental.app.model.repository.ImagenRepository;
import com.zealep.dental.app.service.IImagenService;
import com.zealep.dental.app.util.Constantes;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service("imagenService")
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    ArchivoRepository archivoRepository;

    @Value("${url-path-images-exam}")
    private String URL_PATH_IMAGES_EXAM;

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
    public List<Imagen> findByPaciente(Long id) {
        return imagenRepository.findByPaciente(id,Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Imagen save(Imagen i, MultipartFile file) {
        i.setEstado(Constantes.ESTADO_ACTIVO);
        Imagen img = imagenRepository.save(i);
        if (img.getIdImagen() != null) {
            try {
                Path path = Paths.get(URL_PATH_IMAGES_EXAM + i.getPaciente().getIdPaciente());
                boolean dirExist = Files.exists(path);
                if (!dirExist) {
                    Files.createDirectories(path);
                }

                Path nameImage = Paths.get(img.getIdImagen()+ "-" + file.getOriginalFilename());
                Path targetLocation = path.resolve(nameImage);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                Archivo archivo = new Archivo();
                archivo.setImagen(img);
                archivo.setRuta(targetLocation.toString());
                archivo.setEstado(Constantes.ESTADO_ACTIVO);
                archivoRepository.save(archivo);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return img;
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
        return findById(i.getIdImagen())!=null;
    }
}
