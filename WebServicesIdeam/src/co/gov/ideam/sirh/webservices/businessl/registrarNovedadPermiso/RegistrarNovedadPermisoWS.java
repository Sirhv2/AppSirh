package co.gov.ideam.sirh.webservices.businessl.registrarNovedadPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OE_RegistrarNovedadPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OS_RegistrarNovedadPermiso;

import javax.ejb.Remote;

@Remote
public interface RegistrarNovedadPermisoWS {
    
    public OS_RegistrarNovedadPermiso registrarNovedadPermiso (OE_RegistrarNovedadPermiso oe);
}
