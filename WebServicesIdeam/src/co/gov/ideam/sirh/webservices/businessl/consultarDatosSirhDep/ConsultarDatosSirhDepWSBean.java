package co.gov.ideam.sirh.webservices.businessl.consultarDatosSirhDep;



import co.gov.ideam.sirh.datosena.model.DatosSIRHDep;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarDatosSirhDep.OS_ConsultarDatosSirhDep;


import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarDatosSirhDepWS",
           mappedName = "Sirh-ConsultarDatosSirhDepWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarDatosSirhDep",
            serviceName = "WS_SIAC_SIRH_ConsultarDatosSirhDep",
            portName = "ConsultarDatosSirhDepPort")

public class ConsultarDatosSirhDepWSBean implements ConsultarDatosSirhDepWS {


    @WebMethod(operationName = "ConsultarDatosSirhDep")
    @WebResult(name = "DatosSirhDep")
    public OS_ConsultarDatosSirhDep ConsultarDatosSirhDep() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarDatosSirhDep os = new OS_ConsultarDatosSirhDep();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosSIRHDep> listadatosSIRHDep =
                enad.consultarDatosSIRHDep();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listadatosSIRHDep == null || listadatosSIRHDep.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setDatosSIRHDep(listadatosSIRHDep);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
