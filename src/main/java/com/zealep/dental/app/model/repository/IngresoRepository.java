package com.zealep.dental.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zealep.dental.app.model.entities.Ingreso;
import org.springframework.data.repository.CrudRepository;

public interface IngresoRepository extends CrudRepository<Ingreso,Long> {

	@Query("select i from Ingreso i where i.estado='A'")
	public List<Ingreso> findAllActives();

	@Modifying
	@Query("update Ingreso i set i.estado=?1 where i.idIngreso=?2 ")
	public void deleteLogicById(String estado,Long id);
}
