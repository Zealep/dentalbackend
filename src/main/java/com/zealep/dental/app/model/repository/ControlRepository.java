package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Cita;
import com.zealep.dental.app.model.entities.Control;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ControlRepository extends CrudRepository<Control,Long> {

    @Modifying
    @Query("update Control c set c.estado=?1 where c.idControl=?2 ")
    public void changeStatus(String estado,Long id);

    @Query("select c from Cita c where c.estado <> ?1 ")
    public List<Control> findAllActives(String inactivo);
}
