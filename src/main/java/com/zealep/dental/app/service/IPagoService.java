package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Pago;

import java.util.List;

public interface IPagoService {

    Pago findById(Long id);

    List<Pago> findAll();

    List<Pago> findAllActives();

    Pago save(Pago p);

    Pago update(Pago p);

    void deleteById(Long id);

    boolean isExist(Pago p);
}
