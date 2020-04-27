package com.zealep.dental.app.model.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "odontograma")
public class Odontograma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOdontograma;

    @ManyToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;

    @JsonDeserialize(using= LocalDateDeserializer.class)
    @JsonSerialize(using= ToStringSerializer.class)
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Lob
    @Column(name="image")
    private byte[] image;

    @Column(name = "estado")
    private String estado;

    public Long getIdOdontograma() {
        return idOdontograma;
    }

    public void setIdOdontograma(Long idOdontograma) {
        this.idOdontograma = idOdontograma;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
