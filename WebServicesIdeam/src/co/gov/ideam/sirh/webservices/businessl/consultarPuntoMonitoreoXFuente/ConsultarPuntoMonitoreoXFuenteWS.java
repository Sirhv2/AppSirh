package co.gov.ideam.sirh.webservices.businessl.consultarPuntoMonitoreoXFuente;


import co.gov.ideam.sirh.webservices.model.consultarPuntosMonitoreoXFuente.OE_ConsultarPuntosMonitoreoXFuente;
import co.gov.ideam.sirh.webservices.model.consultarPuntosMonitoreoXFuente.OS_ConsultarPuntosMonitoreoXFuente;

import javax.ejb.Remote;

@Remote
public interface ConsultarPuntoMonitoreoXFuenteWS {
    public OS_ConsultarPuntosMonitoreoXFuente consultarPuntosMonitoreoXFuente(OE_ConsultarPuntosMonitoreoXFuente oe );
}

