package com.zealep.dental.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.service.IImagenService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    IImagenService imagenService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Imagen>> listar() {
        try {
            return new ResponseEntity<List<Imagen>>(imagenService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listByPaciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Imagen>> listarPorPaciente(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Imagen>>(imagenService.findByPaciente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Imagen> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Imagen>(imagenService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestParam(value="file") MultipartFile file,@RequestParam(value="imagen") String imagen) {
        try {
            ObjectMapper obj = new ObjectMapper();
            Imagen img = obj.readValue(imagen,Imagen.class);
            Imagen i = imagenService.save(img,file);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", i.getIdImagen(),""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Imagen imagen) {
        try {
            imagenService.update(imagen);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            imagenService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
