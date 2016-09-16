package co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PermisoNovedad;

import java.io.Serializable;


public class OE_RegistrarNovedadPermiso implements Serializable  {
     
    private String codAutoridadAmbiental;
    private PermisoNovedad permisoNovedad; 

    public OE_RegistrarNovedadPermiso() {
    }
    public void setCodAutoridadAmbiental(String codAutoridadAmbiental) {
        this.codAutoridadAmbiental = codAutoridadAmbiental;
    }

    public String getCodAutoridadAmbiental() {
        return codAutoridadAmbiental;
    }


    public void setPermisoNovedad(PermisoNovedad permisoNovedad) {
        this.permisoNovedad = permisoNovedad;
    }

    public PermisoNovedad getPermisoNovedad() {
        return permisoNovedad;
    }
}
