package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;

public class PomtXArchivosTO implements Serializable {
    private Long archivo;
    private Long plan;

    public PomtXArchivosTO() {
    }

    public Long getArxhivo() {
        return archivo;
    }

    public void setArxhivo(Long archivo) {
        this.archivo = archivo;
    }

    public Long getPlan() {
        return plan;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }
    
    public boolean equals(Object obj) {
        return obj != null && obj instanceof PomtJurisdiccionesTO &&
            this.getPlan() != null &&
            ((PomtJurisdiccionesTO)obj).getJurisdiccion().equals(this.plan);
    }
}

