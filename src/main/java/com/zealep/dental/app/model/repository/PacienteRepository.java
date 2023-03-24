package com.zealep.dental.app.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zealep.dental.app.model.entities.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente,Long>{
	
	@Query("select p from Paciente p where p.estado=?1")
	public List<Paciente> findAllActives(String active);

	@Query(value = "select * from paciente where month(fecha_nacimiento) = month(current_date()) and day(fecha_nacimiento) = day(current_date())",nativeQuery = true)
	public List<Paciente> findBirthdates();

	@Query(value = "select * from paciente where fecha_inicio >= date_add(current_date(),interval - 1 week)",nativeQuery = true)
	public List<Paciente> findNewsPacientes();

	@Modifying
	@Query("update Paciente p set p.estado=?1 where p.idPaciente=?2 ")
	public void deleteLogicById(String estado,Long id);
	
}
