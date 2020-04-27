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

    @Query("select o from Ortodoncia o where o.tratamiento.idTratamiento=?1 and o.estado=?2")
    public Ortodoncia findByTratamiento(Long id,String active);

    @Modifying
    @Query("update Ortodoncia o set o.estado=?1 where o.idOrtodoncia=?2 ")
    public void deleteLogicById(String estado,Long id);

    @Query(value = "select o.* from ortodoncia o inner join tratamiento t on o.id_tratamiento = t.id_tratamiento " +
            "where t.fecha_registro >= date_add(current_date(),interval - 1 month)",nativeQuery = true)
    public List<Ortodoncia> findNewsOrtodoncias();
}
