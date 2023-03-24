package com.zealep.dental.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zealep.dental.app.model.entities.Odontograma;
import com.zealep.dental.app.service.IOdontogramaService;
import com.zealep.dental.app.service.IOdontogramaService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/odontograma")
public class OdontogramaController {

    @Autowired
    IOdontogramaService odontogramaService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Odontograma>> listar() {
        try {
            return new ResponseEntity<List<Odontograma>>(odontogramaService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listByPaciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Odontograma>> listarPorPaciente(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Odontograma>>(odontogramaService.findByPaciente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Odontograma> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Odontograma>(odontogramaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestParam(value="file-image") MultipartFile file, @RequestParam(value="odontograma") String odontograma) {
        try {
            ObjectMapper obj = new ObjectMapper();
            Odontograma img = obj.readValue(odontograma,Odontograma.class);
            Odontograma i = odontogramaService.save(img,file);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", i.getIdOdontograma(),""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Odontograma odontograma) {
        try {
            odontogramaService.update(odontograma);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            odontogramaService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
