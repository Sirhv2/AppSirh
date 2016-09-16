package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;

public class PomtComisionesTO implements Serializable {
    private PomtComisiones comision;
    private String nombreAutoridad;

    public PomtComisionesTO() {
    }

    public PomtComisiones getComision() {
        return comision;
    }

    public void setComision(PomtComisiones comision) {
        this.comision = comision;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }

    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
    }

    public boolean equals(Object obj) {
        return obj != null && obj instanceof PomtComisionesTO &&
            this.getComision() != null &&
            ((PomtComisionesTO)obj).getComision().equals(this.comision);
    }
}

