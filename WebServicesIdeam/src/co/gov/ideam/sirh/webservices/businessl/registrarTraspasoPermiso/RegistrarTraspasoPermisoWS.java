package co.gov.ideam.sirh.webservices.businessl.registrarTraspasoPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OE_TraspasarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OS_TraspasarPermiso;

import javax.ejb.Remote;

@Remote
public interface RegistrarTraspasoPermisoWS {
    
    public OS_TraspasarPermiso registrarTraspasoPermiso (OE_TraspasarPermiso oe);
}
