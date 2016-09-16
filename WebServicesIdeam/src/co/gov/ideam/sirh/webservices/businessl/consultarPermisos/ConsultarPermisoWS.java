package co.gov.ideam.sirh.webservices.businessl.consultarPermisos;


import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OE_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OS_ConsultarPermisos;

import javax.ejb.Remote;

@Remote
public interface ConsultarPermisoWS {
    
    public OS_ConsultarPermisos consultarPermisos (OE_ConsultarPermisos oe);
}
