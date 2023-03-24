package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Cita;
import com.zealep.dental.app.model.entities.Control;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ControlRepository extends CrudRepository<Control,Long> {

    @Query("select c from Control c where c.estado = ?1 ")
    public List<Control> findAllActives(String activo);

    @Query("select c from Control c where c.tratamiento.paciente.idPaciente=?1 and c.estado = ?2 ")
    public List<Control> findByPaciente(Long idPaciente,String estado);

    @Modifying
    @Query("update Control c set c.estado=?1 where c.idControl=?2 ")
    void deleteLogicById(String estado,Long id);
}
