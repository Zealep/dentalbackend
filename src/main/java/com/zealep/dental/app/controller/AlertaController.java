package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Alerta;
import com.zealep.dental.app.service.IAlertaService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
public class AlertaController {
    
    @Autowired
    IAlertaService alertaService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alerta>> listar() {
        try {
            return new ResponseEntity<List<Alerta>>(alertaService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alerta> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Alerta>(alertaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Alerta alerta) {
        try {
            Alerta a = alertaService.save(alerta);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", a.getIdAlerta(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Alerta alerta) {
        try {
            alertaService.update(alerta);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            alertaService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
