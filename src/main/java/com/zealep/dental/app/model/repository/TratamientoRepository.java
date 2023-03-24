package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Ortodoncia;
import com.zealep.dental.app.model.entities.Tratamiento;
import com.zealep.dental.app.model.entities.Tratamiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TratamientoRepository extends CrudRepository<Tratamiento,Long> {

    @Query("select t from Tratamiento t where t.estado=?1")
    public List<Tratamiento> findAllActives(String active);

    @Query("select t from Tratamiento t where t.paciente.idPaciente=?1 and t.estado=?2")
    public List<Tratamiento> findByPaciente(Long id,String active);

    @Query("select t from Tratamiento t where t.paciente.idPaciente=?1 and t.etapa=?2 and t.estado=?3")
    public List<Tratamiento> findByPacienteAndEtapa(Long id,String etapa,String active);

    @Modifying
    @Query("update Tratamiento t set t.estado=?1 where t.idTratamiento=?2 ")
    public void deleteLogicById(String estado,Long id);

    @Modifying
    @Query("update Tratamiento t set t.etapa=?1 where t.idTratamiento=?2 ")
    public void changeEtapa(String etapa,Long id);

    @Query(value = "select * from tratamiento where fecha_registro >= date_add(current_date(),interval - 1 month)",nativeQuery = true)
    public List<Tratamiento> findNewsTratamientos();


}
