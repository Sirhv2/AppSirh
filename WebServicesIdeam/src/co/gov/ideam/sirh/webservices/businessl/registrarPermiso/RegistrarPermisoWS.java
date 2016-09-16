package co.gov.ideam.sirh.webservices.businessl.registrarPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OE_RegistrarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OS_RegistrarPermiso;


import javax.ejb.Remote;

@Remote
public interface RegistrarPermisoWS {
    public OS_RegistrarPermiso RegistrarPermiso (OE_RegistrarPermiso oe);
}

