package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttLogrosPracticasPK implements Serializable {
    public Long idbuenapraactica;
    public Long idlogro;

    public FnttLogrosPracticasPK() {
    }

    public FnttLogrosPracticasPK(Long idbuenapraactica, Long idlogro) {
        this.idbuenapraactica = idbuenapraactica;
        this.idlogro = idlogro;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttLogrosPracticasPK) {
            final FnttLogrosPracticasPK otherFnttLogrosPracticasPK = (FnttLogrosPracticasPK) other;
            final boolean areEqual =
                (otherFnttLogrosPracticasPK.idbuenapraactica.equals(idbuenapraactica) && otherFnttLogrosPracticasPK.idlogro.equals(idlogro));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
