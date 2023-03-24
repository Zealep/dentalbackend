package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.service.IEgresoService;
import com.zealep.dental.app.util.RespuestaApi;
import com.zealep.dental.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/egreso")
public class EgresoController {

    @Autowired
    IEgresoService egresoService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Egreso>> listar() {
        try {
            return new ResponseEntity<List<Egreso>>(egresoService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Egreso> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Egreso>(egresoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Egreso egreso) {
        try {
            Egreso e = egresoService.save(egreso);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", e.getIdEgreso(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Egreso egreso) {
        try {
            egresoService.update(egreso);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {

            egresoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/egresosByDia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findEgresosPorDia() {
        try {
            return new ResponseEntity<Double>(egresoService.findEgresosDia(LocalDate.now()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/egresosByMes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findEgresosPorMes() {
        try {
            return new ResponseEntity<Double>(egresoService.findEgresosMes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/rangeDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Egreso>> findByRangesDates(@RequestParam("fechaInicio") String inicio, @RequestParam("fechaFin")String fin ) {
        try {
            LocalDate fechaInicio = Util.convertStringToLocalDate(inicio);
            LocalDate fechaFin = Util.convertStringToLocalDate(fin);
            return new ResponseEntity<List<Egreso>>(egresoService.findByRangeDates(fechaInicio,fechaFin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
