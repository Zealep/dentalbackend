package com.zealep.dental.app.model.dto;

import com.zealep.dental.app.model.entities.Ortodoncia;
import com.zealep.dental.app.model.entities.Tratamiento;

public class PlanTratamientoDTO {
     private Tratamiento tratamiento;
     private Ortodoncia ortodoncia;

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Ortodoncia getOrtodoncia() {
        return ortodoncia;
    }

    public void setOrtodoncia(Ortodoncia ortodoncia) {
        this.ortodoncia = ortodoncia;
    }
}
