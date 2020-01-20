package com.zealep.dental.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zealep.dental.app.model.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{

	@Query("select d from Doctor d where d.estado='A'")
	public List<Doctor> findAllActives();

	@Modifying
	@Query("update Doctor d set d.estado=?1 where d.idDoctor=?2 ")
	public void deleteLogicById(String estado,Long id);
}
