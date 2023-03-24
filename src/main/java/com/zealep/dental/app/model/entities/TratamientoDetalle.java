package com.zealep.dental.app.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tratamiento_detalle")
public class TratamientoDetalle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtratamiento_detalle")
	private Long idTratamientoDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_tratamiento")
	private Tratamiento tratamiento;
	
	@ManyToOne
	@JoinColumn(name="id_procedimiento")
	private Procedimiento procedimiento;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="cantidad")
	private int cantidad;

	@Column(name="total")
	private double total;
	
	@Column(name="piezas")
	private String piezas;
	
	@Column(name="observacion")
	private String observacion;
	
	
	public Long getIdTratamientoDetalle() {
		return idTratamientoDetalle;
	}
	public void setIdTratamientoDetalle(Long idTratamientoDetalle) {
		this.idTratamientoDetalle = idTratamientoDetalle;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public Procedimiento getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getPiezas() {
		return piezas;
	}
	public void setPiezas(String piezas) {
		this.piezas = piezas;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
