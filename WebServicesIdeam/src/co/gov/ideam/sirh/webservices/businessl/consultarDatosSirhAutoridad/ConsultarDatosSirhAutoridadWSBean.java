package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirhAutoridad;



import co.gov.ideam.sirh.datosena.model.DatosSIRH;
import co.gov.ideam.sirh.datosena.model.DatosSIRHAutoridad;
import co.gov.ideam.sirh.util.IdeamException;


import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;


import co.gov.ideam.sirh.webservices.model.consultarDatosSirhAutoridad.OE_ConsultarDatosSirhAutoridad;
import co.gov.ideam.sirh.webservices.model.consultarDatosSirhAutoridad.OS_ConsultarDatosSirhAutoridad;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarDatosSirhAutoridadWS",
           mappedName = "Sirh-ConsultarDatosSirhAutoridadWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarDatosSirhAutoridad",
            serviceName = "WS_SIAC_SIRH_ConsultarDatosSirhAutoridad",
            portName = "ConsultarDatosSirhAutoridadPort")

public class ConsultarDatosSirhAutoridadWSBean implements ConsultarDatosSirhAutoridadWS {


    @WebMethod(operationName = "ConsultarDatosSirhAutoridad")
    @WebResult(name = "DatosSirhAutoridad")
    public OS_ConsultarDatosSirhAutoridad consultarDatosSirhAutoridad(OE_ConsultarDatosSirhAutoridad oe) {

        
        DatosENAWSDelegate enad;
        OS_ConsultarDatosSirhAutoridad os = new OS_ConsultarDatosSirhAutoridad();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosSIRHAutoridad> listadatosSIRHAutoridad =
                enad.consultarDatosSIRHAutoridad(oe.getCodAutoridadAmbiental());

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listadatosSIRHAutoridad == null || listadatosSIRHAutoridad.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para la autoridad seleccionada");
            }
            os.setDatosSIRHAutoridad(listadatosSIRHAutoridad);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
