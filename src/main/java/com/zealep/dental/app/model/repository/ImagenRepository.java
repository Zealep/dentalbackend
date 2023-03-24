package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.model.entities.Imagen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImagenRepository extends CrudRepository<Imagen,Long> {

    @Query("select i from Imagen i where i.estado=?1")
    public List<Imagen> findAllActives(String active);

    @Query("select i from Imagen i where i.paciente.idPaciente = ?1 and i.estado=?2")
    public List<Imagen> findByPaciente(Long id, String active);

    @Modifying
    @Query("update Imagen i set i.estado=?1 where i.idImagen=?2 ")
    public void deleteLogicById(String estado,Long id);
    
}
