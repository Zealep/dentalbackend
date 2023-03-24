package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.dto.PagoTotalMesesDTO;
import com.zealep.dental.app.model.entities.Ingreso;
import com.zealep.dental.app.model.entities.Pago;
import com.zealep.dental.app.service.IPagoService;
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
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    IPagoService pagoService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pago>> listar() {
        try {
            return new ResponseEntity<List<Pago>>(pagoService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Pago>(pagoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/tratamiento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pago>> buscarPorIdTratamiento(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Pago>>(pagoService.findByIdTratamiento(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Pago pago) {
        try {
            Pago p = pagoService.save(pago);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", p.getIdPago(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Pago pago) {
        try {
            pagoService.update(pago);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            pagoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/meses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PagoTotalMesesDTO>> findPagosTotalMeses() {
        try {
            return new ResponseEntity<List<PagoTotalMesesDTO>>(pagoService.findPagosTotalMeses(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pagosByDia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findPagosPorDia() {
        try {
            return new ResponseEntity<Double>(pagoService.findTotalPagosDia(LocalDate.now()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pagosByMes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> findPagosPorMes() {
        try {
            return new ResponseEntity<Double>(pagoService.findTotalPagosMes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/rangeDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pago>> findByRangesDates(@RequestParam("fechaInicio") String inicio, @RequestParam("fechaFin")String fin ) {
        try {
            LocalDate fechaInicio = Util.convertStringToLocalDate(inicio);
            LocalDate fechaFin = Util.convertStringToLocalDate(fin);
            return new ResponseEntity<List<Pago>>(pagoService.findByRangeDates(fechaInicio,fechaFin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
