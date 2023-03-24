package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    @Modifying
    @Query("update Empresa e set e.logo=?1 where e.idEmpresa=?2")
    public void uploadLogo(byte[] logo,Long id);
}
