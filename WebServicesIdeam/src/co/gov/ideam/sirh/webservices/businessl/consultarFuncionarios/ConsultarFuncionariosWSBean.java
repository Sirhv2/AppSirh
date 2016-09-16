package co.gov.ideam.sirh.webservices.businessl.consultarFuncionarios;

import co.gov.ideam.sirh.seguridad.model.Funcionario;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.SeguridadWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OE_ConsultarFuncionarios;
import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OS_ConsultarFuncionarios;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarFuncionariosSirhWS",
           mappedName = "Sirh-ConsultarFuncionariosWS")
@Remote

@WebService(name = "WS_SIAC_SIRH_ConsultarFuncionarios",
            serviceName = "WS_SIAC_SIRH_ConsultarFuncionarios",
            portName = "ConsultarFuncionariosSirhPort")
public class ConsultarFuncionariosWSBean implements ConsultarFuncionariosWS {


    @WebMethod(operationName = "consultarFuncionarios")
    @WebResult(name = "funcionarios")
    public OS_ConsultarFuncionarios consultarFuncionarios(OE_ConsultarFuncionarios oe) {

        SeguridadWSDelegate sd;
        OS_ConsultarFuncionarios os = new OS_ConsultarFuncionarios();

        try {

            sd = SeguridadWSDelegate.getInstance();

            List<Funcionario> listaF =
                sd.consultarFuncionarios(oe.getCodAutoridadAmbiental());

            os.setCodigoError(0);
            os.setMensajeError("Operacion correcta");

            if (listaF == null || listaF.size() == 0) {
                os.setCodigoError(1);
                os.setMensajeError("No hay usuarios registrados");
            }
            os.setFuncionarios(listaF);

        } catch (IdeamException e) {
            os.setCodigoError(9999);
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
