package com.zealep.dental.app.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import com.zealep.dental.app.model.entities.Paciente;
import org.springframework.web.multipart.MultipartFile;

public interface IPacienteService {
	
	Paciente findById(Long id);
	
	List<Paciente> findAll();
	
	List<Paciente> findAllActives();
	
	Paciente save(Paciente p);
	
	Paciente update(Paciente p);
	
	void deleteById(Long id);

	boolean isExist(Paciente p);

	byte[] obtenerFoto(String path);

	String uploadFoto(MultipartFile file,Long id);

	ByteArrayInputStream exportExcel() throws Exception;

	List<Paciente> buscarCumpleaños();

	List<Paciente> buscarPacientesNuevos();


	
}
