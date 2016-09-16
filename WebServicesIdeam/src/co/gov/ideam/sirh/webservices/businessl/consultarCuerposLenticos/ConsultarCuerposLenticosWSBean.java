package co.gov.ideam.sirh.webservices.businessl.consultarCuerposLenticos;


import co.gov.ideam.sirh.datosena.model.DatosCuerposLenticosTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarCuerposLenticos.OS_ConsultarCL;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarCuerposLenticosSirhWS",
           mappedName = "Sirh-ConsultarCuerposLenticosWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarCuerposLenticos",
             serviceName = "WS_SIAC_SIRH_ConsultarCuerposLenticos",
            portName = "ConsultarCuerposLenticosSirhPort")

public class ConsultarCuerposLenticosWSBean implements ConsultarCuerposLenticosWS {


    @WebMethod(operationName = "consultarCuerposLenticos")
    @WebResult(name = "datosCuerposLenticos")
    public OS_ConsultarCL consultarCL() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarCL os = new OS_ConsultarCL();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosCuerposLenticosTO> listaCuerposLenticos =
                enad.consultarCuerposLenticos();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaCuerposLenticos == null || listaCuerposLenticos.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setListaCuerposLenticos(listaCuerposLenticos);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
