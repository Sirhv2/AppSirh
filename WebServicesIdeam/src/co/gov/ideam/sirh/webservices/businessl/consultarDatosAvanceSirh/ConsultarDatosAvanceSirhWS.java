package co.gov.ideam.sirh.webservices.businessl.consultarDatosAvanceSirh;


import co.gov.ideam.sirh.webservices.model.consultarDatosAvanceSirh.OS_ConsultarDatosAvanceSirh;

import javax.ejb.Remote;

@Remote
public interface ConsultarDatosAvanceSirhWS {
    public OS_ConsultarDatosAvanceSirh ConsultarDatosAvanceSirh ();
}

