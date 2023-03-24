package com.zealep.dental.app.service;

import com.zealep.dental.app.model.dto.PagoTotalMesesDTO;
import com.zealep.dental.app.model.entities.Pago;

import java.time.LocalDate;
import java.util.List;

public interface IPagoService {

    Pago findById(Long id);

    List<Pago> findAll();

    List<Pago> findByIdTratamiento(Long id);

    List<Pago> findAllActives();

    Pago save(Pago p);

    Pago update(Pago p);

    void deleteById(Long id);

    boolean isExist(Pago p);

    List<PagoTotalMesesDTO> findPagosTotalMeses();

    Double findTotalPagosDia(LocalDate date);

    Double findTotalPagosMes();

    List<Pago> findByRangeDates(LocalDate inicio,LocalDate fin);
}
