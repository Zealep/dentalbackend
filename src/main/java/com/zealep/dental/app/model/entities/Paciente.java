package com.zealep.dental.app.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name="paciente")
public class Paciente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_paciente")
	private Long idPaciente;

	@Column(name="nro_historia",nullable = false)
	private String nroHistoria;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="dni")
	private String dni;

	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="direccion")
	private String direccion;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name="lugar_procedencia")
	private String lugarProcedencia;
	
	@Column(name="email")
	private String email;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="estado")
	private String estado;

	@ManyToMany
	@JoinTable(
			name = "paciente_alerta",
			joinColumns = @JoinColumn(name = "id_paciente"),
			inverseJoinColumns = @JoinColumn(name = "id_alerta"))
	Set<Alerta> alertas;

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNroHistoria() {
		return nroHistoria;
	}

	public void setNroHistoria(String nroHistoria) {
		this.nroHistoria = nroHistoria;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLugarProcedencia() {
		return lugarProcedencia;
	}

	public void setLugarProcedencia(String lugarProcedencia) {
		this.lugarProcedencia = lugarProcedencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Set<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(Set<Alerta> alertas) {
		this.alertas = alertas;
	}
}
