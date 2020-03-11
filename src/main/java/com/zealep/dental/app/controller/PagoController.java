package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Pago;
import com.zealep.dental.app.service.IPagoService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

	/*
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Paciente>> listarPagination(Pageable pageable) {
		try {
			return new ResponseEntity<Page<Paciente>>(pacienteService.listAllByPage(pageable), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 */

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<Pago>(pagoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestBody Pago pago) {
        try {
            Pago p = pagoService.save(pago);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> actualizar(@RequestBody Pago pago) {
        try {
            pagoService.update(pago);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
        try {
            pagoService.deleteById(id);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
