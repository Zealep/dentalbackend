package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Pago;
import com.zealep.dental.app.model.entities.Pago;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PagoRepository extends CrudRepository<Pago,Long> {

    @Query("select p from Pago p where p.estado=?1")
    public List<Pago> findAllActives(String active);

    @Modifying
    @Query("update Pago p set p.estado=?1 where p.idPago=?2 ")
    public void deleteLogicById(String estado,Long id);
}
