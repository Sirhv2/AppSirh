package co.gov.ideam.sirh.webservices.businessl.autenticar;


import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.SeguridadWSDelegate;
import co.gov.ideam.sirh.webservices.model.autenticar.OE_Autenticar;
import co.gov.ideam.sirh.webservices.model.autenticar.OS_Autenticar;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "AutenticarSirhWS", mappedName = "Sirh-AutenticarWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_Autenticar",   serviceName = "WS_SIAC_SIRH_Autenticar",
            portName = "AutenticarSirhPort")
public class AutenticarWSBean implements AutenticarWS {


    @WebMethod(operationName = "Autenticar")
    @WebResult(name = "autenticar")
    public OS_Autenticar autenticar(OE_Autenticar oe) {

        SeguridadWSDelegate sd;
        OS_Autenticar os = new OS_Autenticar();

        try {

            sd = SeguridadWSDelegate.getInstance();
            int rta = sd.autenticarUser(oe.getAliasUsuario(), oe.getClave());

            if (rta == 0) {
                os.setCodigoError(rta);
                os.setMensajeError("Operacion correcta");
            } else if (rta == 1) {
                os.setCodigoError(rta);
                os.setMensajeError("Usuario Existe, Clave incorrecta");
            } else if (rta == 2) {
                os.setCodigoError(rta);
                os.setMensajeError("Usuario inactivo");
            } else if (rta == 3) {
                os.setCodigoError(rta);
                os.setMensajeError("Usuario no existe");
            } else if (rta == 4) {
                os.setCodigoError(rta);
                os.setMensajeError("Requiere cambio de clave");
            }
         
        } catch (IdeamException e) {
            os.setCodigoError(9999);
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
