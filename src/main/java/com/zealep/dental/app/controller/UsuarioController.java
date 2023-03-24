package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Usuario;
import com.zealep.dental.app.service.IUsuarioService;
import com.zealep.dental.app.util.RespuestaApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping(value = "/find/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity<Usuario>(usuarioService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> findAll(){
        return new ResponseEntity<List<Usuario>>(usuarioService.findAll(),HttpStatus.OK);
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> save(@RequestBody Usuario usuario){
            Usuario u = usuarioService.save(usuario);
        return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",u.getIdUsuario(),null),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RespuestaApi> update(@RequestBody Usuario usuario){
        Usuario u = usuarioService.update(usuario);
        return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",u.getIdUsuario(),null),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<RespuestaApi> delete(@PathVariable Long id){
            usuarioService.delete(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }
}
