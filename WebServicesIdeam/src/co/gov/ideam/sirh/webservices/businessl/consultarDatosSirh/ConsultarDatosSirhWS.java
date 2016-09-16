package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirh;


import co.gov.ideam.sirh.webservices.model.consultarDatosSirh.OS_ConsultarDatosSirh;

import javax.ejb.Remote;

@Remote
public interface ConsultarDatosSirhWS {
    public OS_ConsultarDatosSirh ConsultarDatosSirh ();
}

