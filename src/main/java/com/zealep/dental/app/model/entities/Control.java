package com.zealep.dental.app.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name="control")
public class Control implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_control")
	private Long idControl;
	
	@ManyToOne
	@JoinColumn(name="id_tratamiento")
	private Tratamiento tratamiento;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="fecha_control")
	private LocalDate fechaControl;
	
	@Column(name="comentarios")
	private String comentarios;

	@Column(name = "estado")
	private String estado;
	
	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public LocalDate getFechaControl() {
		return fechaControl;
	}

	public void setFechaControl(LocalDate fechaControl) {
		this.fechaControl = fechaControl;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
