package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Ortodoncia;
import com.zealep.dental.app.service.IOrtodonciaService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ortodoncia")
public class OrtodonciaController {

    @Autowired
    IOrtodonciaService ortodonciaService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ortodoncia>> listar() {
        try {
            return new ResponseEntity<List<Ortodoncia>>(ortodonciaService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ortodoncia> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Ortodoncia>(ortodonciaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findByTratamiento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ortodoncia> buscarPorTratamiento(@PathVariable Long id) {
        try {
            return new ResponseEntity<Ortodoncia>(ortodonciaService.findByTratamiento(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Ortodoncia ortodoncia) {
        try {
            Ortodoncia o = ortodonciaService.save(ortodoncia);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", o.getIdOrtodoncia(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Ortodoncia ortodoncia) {
        try {
            ortodonciaService.update(ortodoncia);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            ortodonciaService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ortodoncia>> findNewsOrtodoncias() {
        try {
            return new ResponseEntity<List<Ortodoncia>>(ortodonciaService.findNewsOrtodoncia(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
