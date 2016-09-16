package co.gov.ideam.sirh.webservices.businessl.autenticar;


import co.gov.ideam.sirh.webservices.model.autenticar.OE_Autenticar;
import co.gov.ideam.sirh.webservices.model.autenticar.OS_Autenticar;

import javax.ejb.Remote;

@Remote
public interface AutenticarWS {
    public OS_Autenticar autenticar (OE_Autenticar oe);
}

