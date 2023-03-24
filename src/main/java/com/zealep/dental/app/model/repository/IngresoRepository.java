package com.zealep.dental.app.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zealep.dental.app.model.entities.Ingreso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IngresoRepository extends CrudRepository<Ingreso,Long> {

	@Query("select i from Ingreso i where i.estado=?1")
	public List<Ingreso> findAllActives(String active);

	@Query("select i from Ingreso i where i.fechaIngreso between :inicio and :fin and i.estado=:active")
	public List<Ingreso> findByRangeDates(@Param("inicio") LocalDate dateStart,@Param("fin") LocalDate dateEnd,@Param("active") String active);

	@Query(value = "select SUM(monto) from ingreso where fecha_ingreso=?1 and estado=?2" ,nativeQuery = true)
	public Double findTotalIngresosDia(LocalDate date, String activo);

	@Query(value = "select SUM(monto) from ingreso where month(fecha_ingreso) = month(current_date()) and year(fecha_ingreso) = year(current_date()) and estado=?1" ,nativeQuery = true)
	public Double findTotalIngresosMes(String activo);


	@Modifying
	@Query("update Ingreso i set i.estado=?1 where i.idIngreso=?2 ")
	public void deleteLogicById(String estado,Long id);
}
