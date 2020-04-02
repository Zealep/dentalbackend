package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Archivo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArchivoRepository extends CrudRepository<Archivo,Long> {

    @Query("select i from Archivo i where i.imagen.idImagen=?1")
    public Archivo findByImagen(Long idImagen);

    @Query("select i from Archivo i where i.estado=?1")
    public List<Archivo> findAllActives(String active);

    @Modifying
    @Query("update Archivo i set i.estado=?1 where i.idArchivo=?2 ")
    public void deleteLogicById(String estado,Long id);

}
