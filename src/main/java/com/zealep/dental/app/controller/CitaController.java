package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.dto.CitaDTO;
import com.zealep.dental.app.model.entities.Cita;
import com.zealep.dental.app.service.ICitaService;
import com.zealep.dental.app.service.ICitaService;
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
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    ICitaService citaService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cita>> listar() {
        try {
            return new ResponseEntity<List<Cita>>(citaService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cita>> listar(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Cita>>(citaService.findByPaciente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listByDate/{fecha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CitaDTO>> listar(@PathVariable String fecha) {
        try {
            LocalDate localDate = Util.convertStringToLocalDate(fecha);

            return new ResponseEntity<List<CitaDTO>>(citaService.findByDate(localDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Cita>(citaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Cita cita) {
        try {
            Cita c = citaService.save(cita);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", c.getIdCita(),""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Cita cita) {
        try {
            citaService.update(cita);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {

            citaService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/changeEtapa/{id}/{etapa}")
    public ResponseEntity<RespuestaApi> changeEtapa(@PathVariable("id") Long id,@PathVariable("etapa")String etapa){
        try {
            citaService.changeEtapa(id,etapa);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null,""),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
