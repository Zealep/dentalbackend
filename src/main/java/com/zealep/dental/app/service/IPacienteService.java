package com.zealep.dental.app.service;

import java.util.List;

import com.zealep.dental.app.model.entities.Paciente;

public interface IPacienteService {
	
	Paciente findById(Long id);
	
	List<Paciente> findAll();
	
	List<Paciente> findAllActives();
	
	Paciente save(Paciente p);
	
	Paciente update(Paciente p);
	
	void deleteById(Long id);
	
	boolean isExist(Paciente p);
	
}
