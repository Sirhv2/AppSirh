package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirhAutoridad;

import co.gov.ideam.sirh.webservices.model.consultarDatosSirhAutoridad.OE_ConsultarDatosSirhAutoridad;
import co.gov.ideam.sirh.webservices.model.consultarDatosSirhAutoridad.OS_ConsultarDatosSirhAutoridad;
import javax.ejb.Remote;

@Remote
public interface ConsultarDatosSirhAutoridadWS {
    public OS_ConsultarDatosSirhAutoridad consultarDatosSirhAutoridad (OE_ConsultarDatosSirhAutoridad oe);
}

