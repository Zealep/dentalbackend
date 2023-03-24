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
@Table(name="ortodoncia")
public class Ortodoncia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ortodoncia")
	private Long idOrtodoncia;
	
	@ManyToOne
	@JoinColumn(name="id_tratamiento")
	private Tratamiento tratamiento;
	
	@Column(name="meses_tratamiento")
	private int mesesTratamiento;
	
	@Column(name="pago_mensual")
	private double pagoMensual;
	
	@Column(name="dia_pagar")
	private int diaPagar;

	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="f_inst_brackets")
	private LocalDate fechaInstaBrackets;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="f_inst_cont_sup")
	private LocalDate fechaInstaContentSup;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="f_inst_cont_inf")
	private LocalDate fechaInstaContentInf;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="f_inst_aparatologia")
	private LocalDate fechaInstaAparato;
	
	@JsonSerialize(using=ToStringSerializer.class)
	@Column(name="f_inicio_pago")
	private LocalDate fechaInicioPago;
	
	@Column(name="estado")
	private String estado;
	

	public int getMesesTratamiento() {
		return mesesTratamiento;
	}

	public void setMesesTratamiento(int mesesTratamiento) {
		this.mesesTratamiento = mesesTratamiento;
	}

	public double getPagoMensual() {
		return pagoMensual;
	}

	public void setPagoMensual(double pagoMensual) {
		this.pagoMensual = pagoMensual;
	}

	
	public int getDiaPagar() {
		return diaPagar;
	}

	public void setDiaPagar(int diaPagar) {
		this.diaPagar = diaPagar;
	}

	
	public Long getIdOrtodoncia() {
		return idOrtodoncia;
	}

	public void setIdOrtodoncia(Long idOrtodoncia) {
		this.idOrtodoncia = idOrtodoncia;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public LocalDate getFechaInstaBrackets() {
		return fechaInstaBrackets;
	}

	public void setFechaInstaBrackets(LocalDate fechaInstaBrackets) {
		this.fechaInstaBrackets = fechaInstaBrackets;
	}

	public LocalDate getFechaInstaAparato() {
		return fechaInstaAparato;
	}

	public void setFechaInstaAparato(LocalDate fechaInstaAparato) {
		this.fechaInstaAparato = fechaInstaAparato;
	}

	public LocalDate getFechaInstaContentSup() {
		return fechaInstaContentSup;
	}

	public void setFechaInstaContentSup(LocalDate fechaInstaContentSup) {
		this.fechaInstaContentSup = fechaInstaContentSup;
	}


	public LocalDate getFechaInicioPago() {
		return fechaInicioPago;
	}

	public void setFechaInicioPago(LocalDate fechaInicioPago) {
		this.fechaInicioPago = fechaInicioPago;
	}

	public LocalDate getFechaInstaContentInf() {
		return fechaInstaContentInf;
	}

	public void setFechaInstaContentInf(LocalDate fechaInstaContentInf) {
		this.fechaInstaContentInf = fechaInstaContentInf;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	

}
