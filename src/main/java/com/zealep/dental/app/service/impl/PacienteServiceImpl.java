package com.zealep.dental.app.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.model.repository.PacienteRepository;
import com.zealep.dental.app.service.IPacienteService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Service("pacienteService")
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    Path fileStorageLocation;

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return (List<Paciente>) pacienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAllActives() {
        return pacienteRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional
    public Paciente save(Paciente p) {
        p.setEstado(Constantes.ESTADO_ACTIVO);
        return pacienteRepository.save(p);
    }

    @Override
    @Transactional
    public Paciente update(Paciente p) {
        p.setEstado(Constantes.ESTADO_ACTIVO);
        return pacienteRepository.save(p);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pacienteRepository.deleteLogicById(Constantes.ESTADO_INACTIVO, id);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Paciente p) {
        return findById(p.getIdPaciente()) != null;
    }

    @Override
    public byte[] obtenerFoto(String path) {

        byte[] img;
        try {
            BufferedImage bImage = ImageIO.read(new File(path));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            img = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return img;
    }

    @Override
    public String uploadFoto(MultipartFile file, Long id) {


        try {
            String tipo = file.getContentType().substring(file.getContentType().indexOf("/")+1);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Paciente p = this.findById(id);
            String name = p.getApellidos()+"-"+p.getNombres();
            name = name.trim().replace(" ","-");
            name = name + "." + tipo;
            String path = Constantes.URL_PATH_IMAGES + name;
            p.setFoto(path);
            this.update(p);
            Path targetLocation = Paths.get(path);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            return null;
        }

    }
}
