package co.gov.ideam.sirh.webservices.businessl.consultarFuncionarios;

import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OE_ConsultarFuncionarios;
import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OS_ConsultarFuncionarios;

import javax.ejb.Remote;

@Remote
public interface ConsultarFuncionariosWS {
    public OS_ConsultarFuncionarios consultarFuncionarios (OE_ConsultarFuncionarios oe);
}

