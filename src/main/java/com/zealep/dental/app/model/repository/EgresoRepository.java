package com.zealep.dental.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zealep.dental.app.model.entities.Egreso;

public interface EgresoRepository {

	@Query("select e from Egreso e where e.estado='A'")
	public List<Egreso> findAllActives();

	@Modifying
	@Query("update Egreso e set e.estado=?1 where e.idEgreso=?2 ")
	public void deleteLogicById(String estado,Long id);
}
