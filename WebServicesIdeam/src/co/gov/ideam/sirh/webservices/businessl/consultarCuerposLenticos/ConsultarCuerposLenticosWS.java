package co.gov.ideam.sirh.webservices.businessl.consultarCuerposLenticos;


import co.gov.ideam.sirh.webservices.model.consultarCuerposLenticos.OS_ConsultarCL;

import javax.ejb.Remote;

@Remote
public interface ConsultarCuerposLenticosWS {
    public OS_ConsultarCL consultarCL ();
}

