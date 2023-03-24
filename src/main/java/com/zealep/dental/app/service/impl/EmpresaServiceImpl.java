package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.entities.Empresa;
import com.zealep.dental.app.model.repository.EmpresaRepository;
import com.zealep.dental.app.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("empresaService")
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }


    @Override
    public Empresa save(Empresa d) {

        if(d.getIdEmpresa()!=null){
            Empresa e = findById(d.getIdEmpresa());
            d.setLogo(e.getLogo());
        }
        return empresaRepository.save(d);
    }

    @Override
    public Empresa update(Empresa d) {
        return empresaRepository.save(d);
    }

    @Override
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Empresa d) {
        return findById(d.getIdEmpresa())!=null;
    }

    @Override
    @Transactional
    public void uploadLogo(byte[] logo, Long id) {
        empresaRepository.uploadLogo(logo,id);
    }
}
