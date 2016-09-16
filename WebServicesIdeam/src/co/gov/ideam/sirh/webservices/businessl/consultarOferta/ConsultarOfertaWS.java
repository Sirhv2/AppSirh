package co.gov.ideam.sirh.webservices.businessl.consultarOferta;


import co.gov.ideam.sirh.webservices.model.consultarOferta.OS_ConsultarOferta;

import javax.ejb.Remote;

@Remote
public interface ConsultarOfertaWS {
    public OS_ConsultarOferta consultarOferta ();
}

