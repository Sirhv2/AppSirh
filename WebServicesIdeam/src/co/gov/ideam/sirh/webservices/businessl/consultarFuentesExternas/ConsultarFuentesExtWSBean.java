package co.gov.ideam.sirh.webservices.businessl.consultarFuentesExternas;

import co.gov.ideam.sirh.usuarios.agua.model.ConsultasExtFuentesTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarFuentesExternas.OE_ConsultarFuentesExt;
import co.gov.ideam.sirh.webservices.model.consultarFuentesExternas.OS_ConsultarFuentesExt;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarFuentesExtSirhWS",
           mappedName = "Sirh-ConsultarFuentesExtWS")
@Remote

@WebService(name = "WS_SIAC_SIRH_ConsultarFuentesExt",
            serviceName = "WS_SIAC_SIRH_ConsultarFuentesExt",
            portName = "ConsultarFuentesExtSirhPort")
public class ConsultarFuentesExtWSBean implements ConsultarFuentesExtWS {


    @WebMethod(operationName = "ConsultarFuentesExt")
    @WebResult(name = "FuentesEx")
    public OS_ConsultarFuentesExt consultarFuentesExt(OE_ConsultarFuentesExt oe) {

        UsuarioAguaWSDelegate ud;
        OS_ConsultarFuentesExt os = new OS_ConsultarFuentesExt();

        try {

            ud = UsuarioAguaWSDelegate.getInstance();

            List<ConsultasExtFuentesTO> listaFuentes =  ud.getListaFuentesExternas();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaFuentes == null || listaFuentes.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay fuentes registradas");
            }
            os.setFuentesExternas(listaFuentes);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
