package co.gov.ideam.sirh.webservices.businessl.consultarOferta;


import co.gov.ideam.sirh.datosena.model.DatosOfertaAreaTO;


import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;

import co.gov.ideam.sirh.webservices.model.consultarOferta.OS_ConsultarOferta;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarOfertaSirhWS",
           mappedName = "Sirh-ConsultarOfertaWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarOferta",
            serviceName = "WS_SIAC_SIRH_ConsultarOferta",
            portName = "ConsultarOfertaSirhPort")

public class ConsultarOfertaWSBean implements ConsultarOfertaWS {


    @WebMethod(operationName = "consultarOferta")
    @WebResult(name = "datosOfertaArea")
    public OS_ConsultarOferta consultarOferta() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarOferta os = new OS_ConsultarOferta();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosOfertaAreaTO> listaOferta =
                enad.consultarOfertaArea();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaOferta == null || listaOferta.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setOfertaArea(listaOferta);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
