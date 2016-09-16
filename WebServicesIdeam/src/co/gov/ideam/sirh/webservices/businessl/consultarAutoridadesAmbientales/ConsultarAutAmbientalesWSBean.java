package co.gov.ideam.sirh.webservices.businessl.consultarAutoridadesAmbientales;

import co.gov.ideam.sirh.seguridad.model.Funcionario;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OS_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.AutoridadesWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.consultarAutoridadesAmbientales.ConsultarAutAmbientalesWS;
import co.gov.ideam.sirh.webservices.businessl.delegate.SeguridadWSDelegate;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarAutoridadesAmbientales.OE_ConsultarAutAmbientales;
import co.gov.ideam.sirh.webservices.model.consultarAutoridadesAmbientales.OS_ConsultarAutAmbientales;
import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OE_ConsultarFuncionarios;
import co.gov.ideam.sirh.webservices.model.consultarFuncionarios.OS_ConsultarFuncionarios;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarAutAmbientalesSirhWS",
           mappedName = "Sirh-ConsultarAutAmbientalesWS")
@Remote

@WebService(name = "WS_SIAC_SIRH_ConsultarAutoridadesAmbientales",
            serviceName = "WS_SIAC_SIRH_ConsultarAutoridadesAmbientales",
            portName = "ConsultarAutAmbientalesSirhPort")
public class ConsultarAutAmbientalesWSBean implements ConsultarAutAmbientalesWS {


    @WebMethod(operationName = "consultarAutAmbientales")
    @WebResult(name = "AutAmbientales")
    public OS_ConsultarAutAmbientales consultarAutAmbientales (OE_ConsultarAutAmbientales oe) {

      UsuarioAguaWSDelegate usu;
      OS_ConsultarAutAmbientales os = new OS_ConsultarAutAmbientales();

        try {

            usu = UsuarioAguaWSDelegate.getInstance();

            List<AutoridadesWS> listaAutoridades = usu.getAutoridadesAmbientalesWS();
               
            os.setCodigoError(0);
            os.setMensajeError("Operacion correcta");

            if (listaAutoridades == null || listaAutoridades.size() == 0) {
                os.setCodigoError(1);
                os.setMensajeError("No retorna listado ");
            }
            os.setListaAutAmbientales(listaAutoridades);

        } catch (IdeamException e) {
            os.setCodigoError(9999);
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
