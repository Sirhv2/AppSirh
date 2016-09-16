package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttPrincipiosPracticasPK implements Serializable {
    public Long idbuenapraactica;
    public Long idprincipio;

    public FnttPrincipiosPracticasPK() {
    }

    public FnttPrincipiosPracticasPK(Long idbuenapraactica, Long idprincipio) {
        this.idbuenapraactica = idbuenapraactica;
        this.idprincipio = idprincipio;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttPrincipiosPracticasPK) {
            final FnttPrincipiosPracticasPK otherFnttPrincipiosPracticasPK = (FnttPrincipiosPracticasPK) other;
            final boolean areEqual =
                (otherFnttPrincipiosPracticasPK.idbuenapraactica.equals(idbuenapraactica) && otherFnttPrincipiosPracticasPK.idprincipio.equals(idprincipio));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
