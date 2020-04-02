package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.model.dto.PlanTratamientoDTO;
import com.zealep.dental.app.model.dto.TratamientoPagarDTO;
import com.zealep.dental.app.model.entities.Ortodoncia;
import com.zealep.dental.app.model.entities.Tratamiento;
import com.zealep.dental.app.model.repository.OrtodonciaRepository;
import com.zealep.dental.app.model.repository.TratamientoRepository;
import com.zealep.dental.app.model.repository.jdbc.TratamientoJdbcRepository;
import com.zealep.dental.app.service.ITratamientoService;
import com.zealep.dental.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tratamientoService")
public class TratamientoServiceImpl implements ITratamientoService {

    @Autowired
    TratamientoRepository tratamientoRepository;

    @Autowired
    OrtodonciaRepository ortodonciaRepository;

    @Autowired
    TratamientoJdbcRepository tratamientoJdbcRepository;

    @Override
    @Transactional(readOnly = true)
    public Tratamiento findById(Long id) {
        return tratamientoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findAll() {
        return (List<Tratamiento>) tratamientoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findAllActives() {
        return tratamientoRepository.findAllActives(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<TratamientoPagarDTO> findTratamientoPagarByPaciente(Long idPaciente) {
            List<TratamientoPagarDTO> results = (List<TratamientoPagarDTO>) tratamientoJdbcRepository.obtenerPagarPorTratamiento(idPaciente);
            results.forEach(item->{
                item.setSaldoTratamiento(item.getMontoTotalTratamiento()-item.getMontoTotalPagado());
            });

        return results;
    }

    @Override
    @Transactional
    public Tratamiento save(Tratamiento t) {
        t.setEtapa(Constantes.ETAPA_ACTIVA);
        t.setEtapa(Constantes.ETAPA_ACTIVA);
        t.getTratamientoDetalles().forEach(x -> x.setTratamiento(t));
        return tratamientoRepository.save(t);
    }

    @Override
    @Transactional
    public Tratamiento update(Tratamiento t) {
        t.setEtapa(Constantes.ETAPA_ACTIVA);
        t.setEstado(Constantes.ESTADO_ACTIVO);
        t.getTratamientoDetalles().forEach(x -> x.setTratamiento(t));
        return tratamientoRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tratamientoRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Tratamiento t) {
        return findById(t.getIdTratamiento()) != null;
    }

    @Override
    @Transactional
    public boolean savePlanTratamiento(PlanTratamientoDTO planTratamientoDTO) {

        planTratamientoDTO.getTratamiento().setEtapa(Constantes.ETAPA_ACTIVA);
        planTratamientoDTO.getTratamiento().setEstado(Constantes.ESTADO_ACTIVO);
        planTratamientoDTO.getTratamiento().getTratamientoDetalles().forEach(x -> x.setTratamiento(planTratamientoDTO.getTratamiento()));

        Tratamiento t = tratamientoRepository.save(planTratamientoDTO.getTratamiento());

        if (planTratamientoDTO.getOrtodoncia() != null) {
            Ortodoncia o = planTratamientoDTO.getOrtodoncia();
            o.setEstado(Constantes.ESTADO_ACTIVO);
            o.setTratamiento(t);
            ortodonciaRepository.save(o);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean changeEtapa(String etapa, Long id) {
        if (!etapa.equals(Constantes.ETAPA_ACTIVA) && !etapa.equals(Constantes.ETAPA_FINALIZADA)) {
            return false;
        }
        tratamientoRepository.changeEtapa(etapa, id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findByPaciente(Long id) {
        return  tratamientoRepository.findByPaciente(id,Constantes.ESTADO_ACTIVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tratamiento> findByPacienteAndEtapa(Long id, String etapa) {
        return tratamientoRepository.findByPacienteAndEtapa(id,etapa,Constantes.ESTADO_ACTIVO);
    }


}
