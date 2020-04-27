package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Egreso;
import com.zealep.dental.app.model.entities.Empresa;
import com.zealep.dental.app.model.entities.Imagen;
import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.service.IEmpresaService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    IEmpresaService empresaService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id){
        try {
                return new ResponseEntity<Empresa>(empresaService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Empresa>> listar() {
        try {
            return new ResponseEntity<List<Empresa>>(empresaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Empresa empresa) {
        try {
            Empresa e = empresaService.save(empresa);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", e.getIdEmpresa(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Empresa empresa) {
        try {
            empresaService.update(empresa);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            empresaService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/obtenerLogo/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> obtenerLogo(@PathVariable Long id){
        byte[] data = null;
        try {
            Empresa e = empresaService.findById(id);
            return new ResponseEntity<byte[]>(e.getLogo(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(value = "/subirLogo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RespuestaApi> subirFoto(@RequestParam("file") MultipartFile file, @RequestParam("idEmpresa") Long idEmpresa){
        try {
            empresaService.uploadLogo(file.getBytes(),idEmpresa);
            return new ResponseEntity<RespuestaApi>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<RespuestaApi>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
