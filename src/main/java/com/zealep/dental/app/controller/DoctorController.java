package com.zealep.dental.app.controller;

import com.zealep.dental.app.model.entities.Doctor;
import com.zealep.dental.app.service.IDoctorService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	IDoctorService doctorService;
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listar() {
		try {
			return new ResponseEntity<List<Doctor>>(doctorService.findAllActives(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Doctor>> listarPagination(Pageable pageable) {
		try {
			return new ResponseEntity<Page<Doctor>>(DoctorService.listAllByPage(pageable), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 */
	
	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> buscarPorId(@PathVariable Long id) {
		try {
			return new ResponseEntity<Doctor>(doctorService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> registrar(@RequestBody Doctor doctor) {
		try {
			Doctor d = doctorService.save(doctor);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", d.getIdDoctor(), ""), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> actualizar(@RequestBody Doctor doctor) {
		try {
			doctorService.update(doctor);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
		try {
			doctorService.deleteById(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
