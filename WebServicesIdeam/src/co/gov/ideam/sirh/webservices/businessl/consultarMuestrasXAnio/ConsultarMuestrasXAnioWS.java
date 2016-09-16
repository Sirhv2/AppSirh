package co.gov.ideam.sirh.webservices.businessl.consultarMuestrasXAnio;

import co.gov.ideam.sirh.webservices.model.consultarMuestrasXAnio.OE_ConsultarMuestrasXAnio;
import co.gov.ideam.sirh.webservices.model.consultarMuestrasXAnio.OS_ConsultarMuestrasXAnio;
import javax.ejb.Remote;

@Remote
public interface ConsultarMuestrasXAnioWS {
    public OS_ConsultarMuestrasXAnio consultarMuestrasXAnio(OE_ConsultarMuestrasXAnio oe );
}

