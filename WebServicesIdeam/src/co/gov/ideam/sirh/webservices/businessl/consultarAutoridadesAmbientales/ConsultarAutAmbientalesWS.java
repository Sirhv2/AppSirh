package co.gov.ideam.sirh.webservices.businessl.consultarAutoridadesAmbientales;

import co.gov.ideam.sirh.webservices.model.consultarAutoridadesAmbientales.OE_ConsultarAutAmbientales;
import co.gov.ideam.sirh.webservices.model.consultarAutoridadesAmbientales.OS_ConsultarAutAmbientales;
import javax.ejb.Remote;

@Remote
public interface ConsultarAutAmbientalesWS {
    public OS_ConsultarAutAmbientales consultarAutAmbientales (OE_ConsultarAutAmbientales oe);
}

