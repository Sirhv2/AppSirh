package co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PermisoWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PredioWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.RepresentanteWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.UsuarioAguaWS;

import java.io.Serializable;


public class OE_RegistrarPermiso implements Serializable  {
    public OE_RegistrarPermiso() {
    }

   
    private UsuarioAguaWS usuarioAgua;
    private PredioWS predio;
    private RepresentanteWS representanteLegal;
    private PermisoWS permiso;
    private String origen;
    

    public void setUsuarioAgua(UsuarioAguaWS usuarioAgua) {
        this.usuarioAgua = usuarioAgua;
    }

    public UsuarioAguaWS getUsuarioAgua() {
        return usuarioAgua;
    }

    public void setRepresentanteLegal(RepresentanteWS representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public RepresentanteWS getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setPredio(PredioWS predio) {
        this.predio = predio;
    }

    public PredioWS getPredio() {
        return predio;
    }

    public void setPermiso(PermisoWS permiso) {
        this.permiso = permiso;
    }

    public PermisoWS getPermiso() {
        return permiso;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }
}
