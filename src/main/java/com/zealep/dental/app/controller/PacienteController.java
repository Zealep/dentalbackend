package com.zealep.dental.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.service.IPacienteService;
import com.zealep.dental.app.util.RespuestaApi;

@CrossOrigin
@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	IPacienteService pacienteService;
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> listar() {
		try {
			return new ResponseEntity<List<Paciente>>(pacienteService.findAllActives(), HttpStatus.OK);
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
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
		try {
			return new ResponseEntity<Paciente>(pacienteService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> registrar(@RequestBody Paciente paciente) {
		try {
			Paciente p = pacienteService.save(paciente);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> actualizar(@RequestBody Paciente paciente) {
		try {
			pacienteService.update(paciente);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
		try {

			pacienteService.deleteById(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", ""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@PostMapping(value = "/obtenerFoto/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> obtenerFoto(@PathVariable int id){
		byte[] data = null;
		try {
			Paciente p = pacienteService.findById(id);
			String urlPath = p.getFoto();
			if(urlPath!=null) {
			    data = pacienteService.obtenerFoto(urlPath);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	*/
	
}
