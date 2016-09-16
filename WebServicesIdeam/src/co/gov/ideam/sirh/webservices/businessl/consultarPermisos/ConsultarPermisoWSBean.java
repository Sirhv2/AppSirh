package co.gov.ideam.sirh.webservices.businessl.consultarPermisos;


import co.gov.ideam.sirh.usuarios.agua.model.ConsultarPermisosV;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OE_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OS_ConsultarPermisos;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarPermisosSirhWS",
           mappedName = "Sirh-ConsultarPermisosWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarPermisos",
            serviceName = "WS_SIAC_SIRH_ConsultarPermisos",
            portName = "ConsultarPermisoSirhPort")
public class ConsultarPermisoWSBean implements ConsultarPermisoWS {

    @WebMethod(operationName = "ConsultarPermisos")
    @WebResult(name = "ConsultarPermisos")
    public OS_ConsultarPermisos consultarPermisos(OE_ConsultarPermisos oe) {
        UsuarioAguaWSDelegate usu;
        OS_ConsultarPermisos os = new OS_ConsultarPermisos();

        try {

            
            usu = UsuarioAguaWSDelegate.getInstance();
                
            List<ConsultarPermisosV> listap = usu.getPermisosV(oe);
           

            if (listap == null || listap.size() == 0) {
                os.setCodigoError(1);
                os.setMensajeError("No hay permisos registrados");
            }else {
                    os.setCodigoError(0);
                    os.setMensajeError("Operacion correcta"+listap.size());
                    os.setListaPermisos(listap);
                }
          

            } catch (IdeamException e) {
            os.setCodigoError(9999);
            os.setMensajeError("Se genero error" + e.getMessage());
           
            
        } 

        return os;
    }

  

}
