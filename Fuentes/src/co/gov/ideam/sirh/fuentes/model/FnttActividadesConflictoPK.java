package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttActividadesConflictoPK implements Serializable {
    public Long idactividad;
    public Long idconflicto;

    public FnttActividadesConflictoPK() {
    }

    public FnttActividadesConflictoPK(Long idactividad, Long idconflicto) {
        this.idactividad = idactividad;
        this.idconflicto = idconflicto;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttActividadesConflictoPK) {
            final FnttActividadesConflictoPK otherFnttActividadesConflictoPK = (FnttActividadesConflictoPK) other;
            final boolean areEqual =
                (otherFnttActividadesConflictoPK.idactividad.equals(idactividad) && otherFnttActividadesConflictoPK.idconflicto.equals(idconflicto));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
