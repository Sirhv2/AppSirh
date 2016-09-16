package co.gov.ideam.sirh.webservices.businessl.consultarDatosAvanceSirh;


import co.gov.ideam.sirh.datosena.model.DatosAvanceSIRH;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarDatosAvanceSirh.OS_ConsultarDatosAvanceSirh;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarDatosAvanceSirhWS",
           mappedName = "Sirh-ConsultarDatosAvanceSirhWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarDatosAvanceSirh",
            serviceName = "WS_SIAC_SIRH_ConsultarDatosAvanceSirh",
            portName = "ConsultarDatosAvanceSirhPort")

public class ConsultarDatosAvanceSirhWSBean implements ConsultarDatosAvanceSirhWS {


    @WebMethod(operationName = "ConsultarDatosAvanceSirh")
    @WebResult(name = "DatosAvanceSirh")
    public OS_ConsultarDatosAvanceSirh ConsultarDatosAvanceSirh() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarDatosAvanceSirh os = new OS_ConsultarDatosAvanceSirh();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosAvanceSIRH> listaDatosAvanceSirh =
                enad.consultarDatosAvanceSIRH();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaDatosAvanceSirh == null || listaDatosAvanceSirh.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setDatosAvanceSirh(listaDatosAvanceSirh);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
