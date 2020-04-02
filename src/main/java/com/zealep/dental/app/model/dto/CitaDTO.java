package com.zealep.dental.app.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CitaDTO {

    Long idCita;
    String apellidosPaciente;
    String nombresPaciente;
    String apellidosDoctor;
    String nombresDoctor;
    LocalDateTime fechaHora;
    String asunto;
    String etapa;

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }

    public String getNombresPaciente() {
        return nombresPaciente;
    }

    public void setNombresPaciente(String nombresPaciente) {
        this.nombresPaciente = nombresPaciente;
    }

    public String getApellidosDoctor() {
        return apellidosDoctor;
    }

    public void setApellidosDoctor(String apellidosDoctor) {
        this.apellidosDoctor = apellidosDoctor;
    }

    public String getNombresDoctor() {
        return nombresDoctor;
    }

    public void setNombresDoctor(String nombresDoctor) {
        this.nombresDoctor = nombresDoctor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
}
