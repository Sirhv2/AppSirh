package co.gov.ideam.sirh.webservices.businessl.consultarFuentesExternas;


import co.gov.ideam.sirh.webservices.model.consultarFuentesExternas.OE_ConsultarFuentesExt;
import co.gov.ideam.sirh.webservices.model.consultarFuentesExternas.OS_ConsultarFuentesExt;

import javax.ejb.Remote;

@Remote
public interface ConsultarFuentesExtWS {
    public OS_ConsultarFuentesExt consultarFuentesExt (OE_ConsultarFuentesExt oe);
}

