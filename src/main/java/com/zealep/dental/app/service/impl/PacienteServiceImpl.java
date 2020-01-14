package com.zealep.dental.app.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.model.repository.PacienteRepository;
import com.zealep.dental.app.service.IPacienteService;

@Service("pacienteService")
public class PacienteServiceImpl implements IPacienteService{
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Paciente findById(Long id) {
		return pacienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Paciente> findAll() {
		return (List<Paciente>)pacienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Paciente> findAllActives() {
		return pacienteRepository.findAllActives();
	}

	@Override
	@Transactional
	public Paciente save(Paciente p) {
		return pacienteRepository.save(p);
	}

	@Override
	@Transactional
	public Paciente update(Paciente p) {
		return pacienteRepository.save(p);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		pacienteRepository.deleteLogicById(id);
		
	}

}
