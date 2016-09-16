package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttAutoridadFuentePK implements Serializable {
    public Integer id_autoridad;
    public Integer id_fuente;

    public FnttAutoridadFuentePK() {
    }

    public FnttAutoridadFuentePK(Integer id_autoridad, Integer id_fuente) {
        this.id_autoridad = id_autoridad;
        this.id_fuente = id_fuente;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttAutoridadFuentePK) {
            final FnttAutoridadFuentePK otherFnttAutoridadFuentePK = (FnttAutoridadFuentePK) other;
            final boolean areEqual =
                (otherFnttAutoridadFuentePK.id_autoridad.equals(id_autoridad) && otherFnttAutoridadFuentePK.id_fuente.equals(id_fuente));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
