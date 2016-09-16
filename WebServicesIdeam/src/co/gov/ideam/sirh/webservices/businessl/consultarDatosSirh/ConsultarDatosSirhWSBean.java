package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirh;



import co.gov.ideam.sirh.datosena.model.DatosSIRH;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;

import co.gov.ideam.sirh.webservices.model.consultarDatosSirh.OS_ConsultarDatosSirh;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarDatosSirhWS",
           mappedName = "Sirh-ConsultarDatosSirhWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarDatosSirh",
            serviceName = "WS_SIAC_SIRH_ConsultarDatosSirh",
            portName = "ConsultarDatosSirhPort")

public class ConsultarDatosSirhWSBean implements ConsultarDatosSirhWS {


    @WebMethod(operationName = "ConsultarDatosSirh")
    @WebResult(name = "DatosSirh")
    public OS_ConsultarDatosSirh ConsultarDatosSirh() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarDatosSirh os = new OS_ConsultarDatosSirh();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosSIRH> listadatosSIRH =
                enad.consultarDatosSIRH();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listadatosSIRH == null || listadatosSIRH.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setDatosSIRH(listadatosSIRH);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
