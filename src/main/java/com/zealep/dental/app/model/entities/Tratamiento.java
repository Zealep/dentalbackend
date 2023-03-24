package com.zealep.dental.app.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name="tratamiento")
public class Tratamiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tratamiento")
	private Long idTratamiento;
	
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="id_doctor")
	private Doctor doctor;
	
	@OneToMany(mappedBy="tratamiento",cascade=CascadeType.ALL)
	private List<TratamientoDetalle> tratamientoDetalles;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="fecha_registro")
	private LocalDate fechaRegistro;

	@Column(name="nombre")
	private String nombre;

	@Column(name="etapa")
	private String etapa;

	@Column(name="monto")
	private double monto;
	
	@Column(name="descuento")
	private double descuento;
	
	@Column(name="monto_total")
	private double montoTotal;
	
	@Column(name="comentarios")
	private String comentarios;
	
	@Column(name="estado")
	private String estado;
	

	public Long getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(Long idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}


	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<TratamientoDetalle> getTratamientoDetalles() {
		return tratamientoDetalles;
	}

	public void setTratamientoDetalles(List<TratamientoDetalle> tratamientoDetalles) {
		this.tratamientoDetalles = tratamientoDetalles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
}
