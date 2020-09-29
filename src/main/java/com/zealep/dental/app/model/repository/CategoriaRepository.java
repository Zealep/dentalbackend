package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Categoria;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria,Long> {

    @Query("select c from Categoria c where c.estado=?1")
    List<Categoria> findAllActives(String estado);

    @Modifying
    @Query("update Categoria c set c.estado=?1 where c.idCategoria=?2 ")
    public void deleteLogicById(String estado,Long id);

}
