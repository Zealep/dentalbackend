package com.zealep.dental.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zealep.dental.app.model.entities.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente,Long>{
	
	@Query("select p from Paciente p where p.estado='A'")
	public List<Paciente> findAllActives();

	@Modifying
	@Query("update Paciente p set p.estado=?1 where p.idPaciente=?2 ")
	public void deleteLogicById(String estado,Long id);
	
}
