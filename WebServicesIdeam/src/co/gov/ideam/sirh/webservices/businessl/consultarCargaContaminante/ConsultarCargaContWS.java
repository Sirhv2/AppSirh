package co.gov.ideam.sirh.webservices.businessl.consultarCargaContaminante;



import co.gov.ideam.sirh.webservices.model.consultarCargaContaminante.OS_ConsultarCargaCont;
import javax.ejb.Remote;

@Remote
public interface ConsultarCargaContWS {
    public OS_ConsultarCargaCont consultarCargaCont ();
}

