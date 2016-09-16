package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

public class FnttLogrosConflictoPK implements Serializable {
    public Long idconflicto;
    public Long idlogro;

    public FnttLogrosConflictoPK() {
    }

    public FnttLogrosConflictoPK(Long idconflicto, Long idlogro) {
        this.idconflicto = idconflicto;
        this.idlogro = idlogro;
    }

    public boolean equals(Object other) {
        if (other instanceof FnttLogrosConflictoPK) {
            final FnttLogrosConflictoPK otherFnttLogrosConflictoPK = (FnttLogrosConflictoPK) other;
            final boolean areEqual =
                (otherFnttLogrosConflictoPK.idconflicto.equals(idconflicto) && otherFnttLogrosConflictoPK.idlogro.equals(idlogro));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
