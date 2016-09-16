package co.gov.ideam.sirh.webservices.businessl.consultarAreaPomcas;


import co.gov.ideam.sirh.webservices.model.consultarAreaPomcas.OS_ConsultarAreaPomcas;
import javax.ejb.Remote;

@Remote
public interface ConsultarAreaPomcasWS {
    public OS_ConsultarAreaPomcas consultarAreaPomcas ();
}

