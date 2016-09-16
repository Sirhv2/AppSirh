package co.gov.ideam.sirh.webservices.businessl.consultarCaptacionXFuente;


import co.gov.ideam.sirh.webservices.model.consultarCaptacionXFuente.OE_ConsultarCaptacionXFuente;
import co.gov.ideam.sirh.webservices.model.consultarCaptacionXFuente.OS_ConsultarCaptacionXFuente;

import javax.ejb.Remote;

@Remote
public interface ConsultarCaptacionXFuenteWS {
    public OS_ConsultarCaptacionXFuente consultarCaptacionXFuente(OE_ConsultarCaptacionXFuente oe );
}

