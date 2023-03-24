package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Cita;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitaRepository extends CrudRepository<Cita,Long> {

    @Modifying
    @Query("update Cita c set c.etapa=?1 where c.idCita=?2 ")
    public void changeStatus(String etapa,Long id);

    @Query("select c from Cita c where c.estado = ?1 ")
    public List<Cita> findAllActives(String estado);

    @Query("select c from Cita c where c.paciente.idPaciente = ?1 and c.estado = ?2 order by c.fechaHora desc")
    public List<Cita> findByPaciente(Long id,String estado);

    @Modifying
    @Query("update Cita c set c.estado=?1 where c.idCita=?2 ")
    public void deleteLogicById(String estado,Long id);
}
