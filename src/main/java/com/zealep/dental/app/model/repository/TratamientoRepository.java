package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Tratamiento;
import com.zealep.dental.app.model.entities.Tratamiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TratamientoRepository extends CrudRepository<Tratamiento,Long> {

    @Query("select t from Tratamiento t where t.estado=?1")
    public List<Tratamiento> findAllActives(String active);

    @Modifying
    @Query("update Tratamiento t set t.estado=?1 where t.idTratamiento=?2 ")
    public void deleteLogicById(String estado,Long id);
}
