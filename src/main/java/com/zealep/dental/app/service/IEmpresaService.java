package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Empresa;

import java.util.List;

public interface IEmpresaService {

    Empresa findById(Long id);

    List<Empresa> findAll();

    Empresa save(Empresa d);

    Empresa update(Empresa d);

    void deleteById(Long id);

    boolean isExist(Empresa d);

    void uploadLogo(byte[] logo,Long id);
}
