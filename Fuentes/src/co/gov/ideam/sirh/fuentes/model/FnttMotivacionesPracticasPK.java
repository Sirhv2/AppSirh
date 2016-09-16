package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttMotivacionesPracticasPK implements Serializable {
    public Long idbuenapraactica;
    public Long idmotivacion;

    public FnttMotivacionesPracticasPK() {
    }

    public FnttMotivacionesPracticasPK(Long idbuenapraactica,
                                       Long idmotivacion) {
        this.idbuenapraactica = idbuenapraactica;
        this.idmotivacion = idmotivacion;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttMotivacionesPracticasPK) {
            final FnttMotivacionesPracticasPK otherFnttMotivacionesPracticasPK = (FnttMotivacionesPracticasPK) other;
            final boolean areEqual =
                (otherFnttMotivacionesPracticasPK.idbuenapraactica.equals(idbuenapraactica) && otherFnttMotivacionesPracticasPK.idmotivacion.equals(idmotivacion));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
