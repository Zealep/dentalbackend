package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Cita;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitaRepository extends CrudRepository<Cita,Long> {

    @Modifying
    @Query("update Cita c set c.estado=?1 where c.idCita=?2 ")
    public void changeStatus(String estado,Long id);

    @Query("select c from Cita c where c.estado <> ?1 ")
    public List<Cita> findAllActives(String inactivo);
}
