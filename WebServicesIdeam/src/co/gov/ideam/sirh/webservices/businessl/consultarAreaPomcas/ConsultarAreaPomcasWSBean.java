package co.gov.ideam.sirh.webservices.businessl.consultarAreaPomcas;


import co.gov.ideam.sirh.datosena.model.DatosAreaPomcas;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarAreaPomcas.OS_ConsultarAreaPomcas;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarAreaPomcasSirhWS",
           mappedName = "Sirh-ConsultarAreaPomcasWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_AreaObjetoPomca",
            serviceName = "WS_SIAC_SIRH_AreaObjetoPomca",
            portName = "ConsultarAreaPomcasSirhPort")

public class ConsultarAreaPomcasWSBean implements ConsultarAreaPomcasWS {


    @WebMethod(operationName = "consultarAreaPomcas")
    @WebResult(name = "datosAreaPomcas")
    public OS_ConsultarAreaPomcas consultarAreaPomcas() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarAreaPomcas os = new OS_ConsultarAreaPomcas();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosAreaPomcas> listadatosPomca =
                enad.consultarDatosAreaPomcas();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listadatosPomca == null || listadatosPomca.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setDatosAreaPomcas(listadatosPomca);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
