package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.model.entities.Odontograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OdontogramaRepository extends JpaRepository<Odontograma, Long> {

    @Query("select o from Odontograma o where o.estado=?1")
    public List<Odontograma> findAllActives(String active);

    @Query("select o from Odontograma o where o.paciente.idPaciente = ?1 and o.estado=?2")
    public List<Odontograma> findByPaciente(Long id, String active);

    @Modifying
    @Query("update Odontograma o set o.estado=?1 where o.idOdontograma=?2 ")
    public void deleteLogicById(String estado,Long id);

}
