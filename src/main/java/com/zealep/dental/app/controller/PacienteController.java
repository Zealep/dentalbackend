package com.zealep.dental.app.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.zealep.dental.app.model.entities.Paciente;
import com.zealep.dental.app.service.IPacienteService;
import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.web.multipart.MultipartFile;


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
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",p.getIdPaciente(), ""), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/saveAlertas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> registrarAlertas(@RequestBody Paciente paciente) {
		try {
			Paciente p = pacienteService.save(paciente);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",p.getIdPaciente(), ""), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> actualizar(@RequestBody Paciente paciente) {
		try {
			pacienteService.update(paciente);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", null,""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminar(@PathVariable long id) {
		try {

			pacienteService.deleteById(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK" ,null,""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping(value = "/obtenerFoto/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> obtenerFoto(@PathVariable Long id){
		byte[] data = null;
		try {
			Paciente p = pacienteService.findById(id);
			String urlPath = p.getFoto();
			if(urlPath!=null) {
			    data = pacienteService.obtenerFoto(urlPath);
			}
			return new ResponseEntity<byte[]>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(value = "/subirFoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<RespuestaApi> subirFoto(@RequestParam("file") MultipartFile file, @RequestParam("idPaciente") Long idPaciente){
		try {
			pacienteService.uploadFoto(file,idPaciente);
			return new ResponseEntity<RespuestaApi>(HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<RespuestaApi>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping(value = "/export")
	public ResponseEntity<InputStreamSource> exportarExcel(){
		try {
			ByteArrayInputStream stream = pacienteService.exportExcel();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=pacientes.xls");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/onomastico")
	public ResponseEntity<List<Paciente>> buscarCumpleaños(){
		try {
			return new ResponseEntity<List<Paciente>>(pacienteService.buscarCumpleaños(),HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/nuevos")
	public ResponseEntity<List<Paciente>> buscarPacientesNuevos(){
		try {
			return new ResponseEntity<List<Paciente>>(pacienteService.buscarPacientesNuevos(),HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
}
