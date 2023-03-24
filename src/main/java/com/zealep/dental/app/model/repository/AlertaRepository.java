package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Alerta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlertaRepository extends CrudRepository<Alerta,Long> {

    @Query("select a from Alerta a where a.estado=?1")
    List<Alerta> findAllActives(String estado);

    @Modifying
    @Query("update Alerta a set a.estado=?1 where a.idAlerta=?2 ")
    void deleteLogicById(String estado,Long id);
}
