package co.gov.ideam.sirh.webservices.businessl.consultarCargaContaminante;


import co.gov.ideam.sirh.datosena.model.DatosCargaContaminante;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarCargaContaminante.OS_ConsultarCargaCont;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarCargaContSirhWS",
           mappedName = "Sirh-ConsultarCargaContWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarCargaCont",
            serviceName = "WS_SIAC_SIRH_ConsultarCargaCont",
            portName = "ConsultarCargaContSirhPort")

public class ConsultarCargaContWSBean implements ConsultarCargaContWS {


    @WebMethod(operationName = "consultarCargaCont")
    @WebResult(name = "datosCargaCont")
    public OS_ConsultarCargaCont consultarCargaCont() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarCargaCont os = new OS_ConsultarCargaCont();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosCargaContaminante> listaCargaCont =
                enad.consultarDatosCargaContaminante();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaCargaCont == null || listaCargaCont.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setDatosCarga(listaCargaCont);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
