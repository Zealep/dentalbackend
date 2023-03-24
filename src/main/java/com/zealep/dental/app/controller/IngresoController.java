package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.service.IIngresoService;
import com.zealep.dental.app.util.RespuestaApi;
import com.zealep.dental.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/ingreso")
public class IngresoController {

    @Autowired
    IIngresoService ingresoService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ingreso>> listar() {
        try {
            return new ResponseEntity<List<Ingreso>>(ingresoService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingreso> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Ingreso>(ingresoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Ingreso ingreso) {
        try {
            Ingreso p = ingresoService.save(ingreso);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", p.getIdIngreso(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Ingreso ingreso) {
        try {
            ingresoService.update(ingreso);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {

            ingresoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ingresosByDia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findIngresosPorDia() {
        try {
            return new ResponseEntity<Double>(ingresoService.findIngresosDia(LocalDate.now()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ingresosByMes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findIngresosPorMes() {
        try {
            return new ResponseEntity<Double>(ingresoService.findIngresosMes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/rangeDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ingreso>> findByRangesDates(@RequestParam("fechaInicio") String inicio,@RequestParam("fechaFin")String fin ) {
        try {
            LocalDate fechaInicio = Util.convertStringToLocalDate(inicio);
            LocalDate fechaFin = Util.convertStringToLocalDate(fin);
            return new ResponseEntity<List<Ingreso>>(ingresoService.findByRangeDates(fechaInicio,fechaFin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
