package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.entities.Procedimiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcedimientoRepository extends CrudRepository<Procedimiento, Long> {

    @Query("select e from Procedimiento e where e.estado=?1")
    public List<Procedimiento> findAllActives(String active);

    @Modifying
    @Query("update Procedimiento e set e.estado=?1 where e.idProcedimiento=?2 ")
    public void deleteLogicById(String estado,Long id);
}
