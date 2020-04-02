package com.zealep.dental.app.model.dto;

public class TratamientoPagarDTO {

    Long   idTratamiento;
    String nombreTratamiento;
    String apellidosDoctor;
    String nombresDoctor;
    String apellidosPaciente;
    String nombresPaciente;
    String documentoIdentidadPaciente;
    double montoTotalTratamiento;
    double montoTotalPagado;
    double saldoTratamiento;

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
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

    public String getDocumentoIdentidadPaciente() {
        return documentoIdentidadPaciente;
    }

    public void setDocumentoIdentidadPaciente(String documentoIdentidadPaciente) {
        this.documentoIdentidadPaciente = documentoIdentidadPaciente;
    }

    public double getMontoTotalTratamiento() {
        return montoTotalTratamiento;
    }

    public void setMontoTotalTratamiento(double montoTotalTratamiento) {
        this.montoTotalTratamiento = montoTotalTratamiento;
    }

    public double getMontoTotalPagado() {
        return montoTotalPagado;
    }

    public void setMontoTotalPagado(double montoTotalPagado) {
        this.montoTotalPagado = montoTotalPagado;
    }

    public double getSaldoTratamiento() {
        return saldoTratamiento;
    }

    public void setSaldoTratamiento(double saldoTratamiento) {
        this.saldoTratamiento = saldoTratamiento;
    }
}
