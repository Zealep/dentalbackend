package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.entities.Pago;
import com.zealep.dental.app.model.entities.Pago;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagoRepository extends CrudRepository<Pago,Long> {

    @Query("select p from Pago p where p.estado=?1")
    public List<Pago> findAllActives(String active);

    @Query("select p from Pago p where p.tratamiento.idTratamiento=?1 and p.estado=?2")
    public List<Pago> findByTratamiento(Long idTratamiento,String active);

    @Query("select p from Pago p where p.fechaPago between :inicio and :fin and p.estado=:active")
    public List<Pago> findByRangeDates(@Param("inicio") LocalDate dateStart, @Param("fin") LocalDate dateEnd, @Param("active") String active);

    @Query(value = "select SUM(monto) from pago where fecha_pago=?1 and estado=?2" ,nativeQuery = true)
    public Double findTotalPagosDia(LocalDate date,String activo);

    @Query(value = "select SUM(monto) from pago where month(fecha_pago) = month(current_date()) and year(fecha_pago) = year(current_date()) and estado=?1" ,nativeQuery = true)
    public Double findTotalPagosMes(String activo);

    @Modifying
    @Query("update Pago p set p.estado=?1 where p.idPago=?2 ")
    public void deleteLogicById(String estado,Long id);
}
