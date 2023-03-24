package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto,Long> {
    @Query("select p from Producto p where p.estado=?1")
    List<Producto> findAllActives(String estado);

    @Modifying
    @Query("update Producto c set c.estado=?1 where c.idProducto=?2 ")
    void deleteLogicById(String estado,Long id);
}
