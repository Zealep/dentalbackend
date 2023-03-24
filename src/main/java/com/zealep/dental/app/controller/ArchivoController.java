package com.zealep.dental.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zealep.dental.app.model.entities.Archivo;
import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.service.IArchivoService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/archivo")
public class ArchivoController {

    @Autowired
    IArchivoService archivoService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Archivo>> listar() {
        try {
            return new ResponseEntity<List<Archivo>>(archivoService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Archivo> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Archivo>(archivoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestParam(value="file") MultipartFile file,@RequestParam(value="Archivo") String Archivo) {
        try {
            ObjectMapper obj = new ObjectMapper();
            Archivo img = obj.readValue(Archivo,Archivo.class);
            Archivo i = archivoService.save(img);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", i.getIdArchivo(),""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Archivo Archivo) {
        try {
            archivoService.update(Archivo);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            archivoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/obtenerImagen/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id){
        byte[] data = null;
        try {
            Archivo p = archivoService.findByImagen(id);
            String urlPath = p.getRuta();
            if(urlPath!=null) {
                data = archivoService.obtenerImagen(urlPath);
            }
            return new ResponseEntity<byte[]>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
