package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirhDep;


import co.gov.ideam.sirh.webservices.model.consultarDatosSirhDep.OS_ConsultarDatosSirhDep;

import javax.ejb.Remote;

@Remote
public interface ConsultarDatosSirhDepWS {
    public OS_ConsultarDatosSirhDep ConsultarDatosSirhDep();
}

