package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.dto.PlanTratamientoDTO;
import com.zealep.dental.app.model.dto.TratamientoPagarDTO;
import com.zealep.dental.app.model.entities.Tratamiento;
import com.zealep.dental.app.service.ITratamientoService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tratamiento")
public class TratamientoController {

    @Autowired
    ITratamientoService tratamientoService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tratamiento>> listar() {
        try {
            return new ResponseEntity<List<Tratamiento>>(tratamientoService.findAllActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tratamiento> buscarPorId(@PathVariable(required = true) Long id) {
        try {
            return new ResponseEntity<Tratamiento>(tratamientoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findTratamientoPagar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TratamientoPagarDTO>> buscarTratamientosPagar(@PathVariable(required = true) Long id) {
        try {
            return new ResponseEntity<List<TratamientoPagarDTO>>(tratamientoService.findTratamientoPagarByPaciente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Tratamiento tratamiento) {
        try {
            Tratamiento t = tratamientoService.save(tratamiento);
            Object o = new Object();

            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", t.getIdTratamiento(), ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Tratamiento tratamiento) {
        try {
            tratamientoService.update(tratamiento);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable(required = true) long id) {
        try {
            tratamientoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/savePlanTratamiento", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> savePlanTratamiento(@RequestBody PlanTratamientoDTO planTratamientoDTO) {
        try {
            tratamientoService.savePlanTratamiento(planTratamientoDTO);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping(value = "/findByPaciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tratamiento>> buscarPorPaciente(@PathVariable(required = true) Long id) {
        try {
            return new ResponseEntity<List<Tratamiento>>(tratamientoService.findByPaciente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findByPacienteAndEtapa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tratamiento>> buscarPorPaciente(@RequestParam(required = true) Long id,@RequestParam(required = true) String etapa) {
        try {
            return new ResponseEntity<List<Tratamiento>>(tratamientoService.findByPacienteAndEtapa(id,etapa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value= "/changeEtapa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> changeEtapa(@RequestParam Long id,@RequestParam(required = true) String etapa){
        try {
                tratamientoService.changeEtapa(etapa,id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null, ""), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/generarContrato", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarContrato(@RequestBody Tratamiento tratamiento){
        byte[] data = null;
        try {
            data = tratamientoService.generarContrato(tratamiento);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tratamiento>> findNewsTratamientos() {
        try {
            return new ResponseEntity<List<Tratamiento>>(tratamientoService.findNewsTratamientos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
