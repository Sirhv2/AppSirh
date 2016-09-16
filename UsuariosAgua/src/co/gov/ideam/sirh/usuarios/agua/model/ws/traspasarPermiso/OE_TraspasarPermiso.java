package co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PermisoTraspaso;

import java.io.Serializable;


public class OE_TraspasarPermiso implements Serializable  {
     
    private String codAutoridadAmbiental;
    private PermisoTraspaso permisoTraspaso; 

    public OE_TraspasarPermiso() {
    }
    public void setCodAutoridadAmbiental(String codAutoridadAmbiental) {
        this.codAutoridadAmbiental = codAutoridadAmbiental;
    }

    public String getCodAutoridadAmbiental() {
        return codAutoridadAmbiental;
    }


    public void setPermisoTraspaso(PermisoTraspaso permisoTraspaso) {
        this.permisoTraspaso = permisoTraspaso;
    }

    public PermisoTraspaso getPermisoTraspaso() {
        return permisoTraspaso;
    }
}
