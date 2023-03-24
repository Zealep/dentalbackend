package com.zealep.dental.app.model.repository;

import java.time.LocalDate;
import java.util.List;

import com.zealep.dental.app.model.entities.Ingreso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zealep.dental.app.model.entities.Egreso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EgresoRepository extends CrudRepository<Egreso,Long> {

	@Query("select e from Egreso e where e.estado=?1")
	public List<Egreso> findAllActives(String active);

	@Query("select e from Egreso e where e.fechaEgreso between :inicio and :fin and e.estado=:active")
	public List<Egreso> findByRangeDates(@Param("inicio") LocalDate dateStart, @Param("fin") LocalDate dateEnd, @Param("active") String active);

	@Query(value = "select SUM(costo) from egreso where fecha_egreso=?1 and estado=?2" ,nativeQuery = true)
	public Double findTotalEgresosDia(LocalDate date, String activo);

	@Query(value = "select SUM(costo) from egreso where month(fecha_egreso) = month(current_date()) and year(fecha_egreso) = year(current_date()) and estado=?1" ,nativeQuery = true)
	public Double findTotalEgresosMes(String activo);

	@Modifying
	@Query("update Egreso e set e.estado=?1 where e.idEgreso=?2 ")
	public void deleteLogicById(String estado,Long id);
}
