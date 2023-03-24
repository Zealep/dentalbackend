package com.zealep.dental.app.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${url-path-images}")
    private String URL_PATH_IMAGES;

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


        try {
            if(path!=null && !path.isEmpty()){
                return FileUtils.readFileToByteArray(new File(FilenameUtils.separatorsToSystem(path)));
            }
            else{
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String uploadFoto(MultipartFile file, Long id) {

        try {
            String tipo = file.getContentType().substring(file.getContentType().indexOf("/") + 1);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Paciente p = this.findById(id);
            String name = p.getApellidos() + "-" + p.getNombres();
            name = name.trim().replace(" ", "-");
            name = name + "." + tipo;
            String path = URL_PATH_IMAGES + name;
            p.setFoto(path);
            this.update(p);
            Path targetLocation = Paths.get(path);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            return null;
        }

    }

    @Override
    public ByteArrayInputStream exportExcel() throws Exception {
        String[] headers = {"Id Paciente", "Nro Historia", "Apellidos", "Nombres", "DNI", "Fecha Nacimiento"
                , "Telefono", "Celular", "Direccion", "Fecha Inicio", "Lugar Procedencia", "Email"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
        CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

        Sheet sheet = workbook.createSheet("Pacientes");
        sheet.setDefaultColumnWidth(20);


        Row row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);

        }

        List<Paciente> pacientes = new ArrayList<>();
        int initRow = 1;
        for (Paciente p : findAllActives()) {
            row = sheet.createRow(initRow);
            row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

            ExcelUtil.createLongCell(p.getIdPaciente(),row,0,rowStyle);
            ExcelUtil.createStringCell(p.getNroHistoria(),row,1,rowStyle);
            ExcelUtil.createStringCell(p.getApellidos(),row,2,rowStyle);
            ExcelUtil.createStringCell(p.getNombres(),row,3,rowStyle);
            ExcelUtil.createStringCell(p.getDni(),row,4,rowStyle);
            ExcelUtil.createStringCell(p.getFechaNacimiento()!=null?p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyy")):"",row,5,rowStyle);
            ExcelUtil.createStringCell(p.getTelefono(),row,6,rowStyle);
            ExcelUtil.createStringCell(p.getCelular(),row,7,rowStyle);
            ExcelUtil.createStringCell(p.getDireccion(),row,8,rowStyle);
            ExcelUtil.createStringCell(p.getFechaInicio()!=null?p.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyy")):"",row,9,rowStyle);
            ExcelUtil.createStringCell(p.getLugarProcedencia(),row,10,rowStyle);
            ExcelUtil.createStringCell(p.getEmail(),row,11,rowStyle);

            row.setRowStyle(rowStyle);
            initRow++;
        }

        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }

    @Override
    public List<Paciente> buscarCumpleaños() {
        return pacienteRepository.findBirthdates();
    }

    @Override
    public List<Paciente> buscarPacientesNuevos() {
        return pacienteRepository.findNewsPacientes();
    }
}
