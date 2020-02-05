package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.model.entities.Ortodoncia;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrtodonciaRepository extends CrudRepository<Ortodoncia,Long> {

    @Query("select o from Ortodoncia o where o.estado=?1")
    public List<Ortodoncia> findAllActives(String active);

    @Modifying
    @Query("update Ortodoncia o set o.estado=?1 where o.idOrtodoncia=?2 ")
    public void deleteLogicById(String estado,Long id);
}
