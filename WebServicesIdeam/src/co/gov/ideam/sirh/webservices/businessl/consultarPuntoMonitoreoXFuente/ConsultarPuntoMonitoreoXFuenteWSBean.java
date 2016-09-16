package co.gov.ideam.sirh.webservices.businessl.consultarPuntosMonitoreoXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.consultarPuntoMonitoreoXFuente.ConsultarPuntoMonitoreoXFuenteWS;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarPuntosMonitoreoXFuente.OE_ConsultarPuntosMonitoreoXFuente;
import co.gov.ideam.sirh.webservices.model.consultarPuntosMonitoreoXFuente.OS_ConsultarPuntosMonitoreoXFuente;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarPuntosMonitoreoXFuenteSirhWS",
           mappedName = "Sirh-ConsultarPuntosMonitoreoXFuenteWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarPuntosMonitoreoXFuente",
            serviceName = "WS_SIAC_SIRH_ConsultarPuntosMonitoreoXFuente",
            portName = "ConsultarPuntosMonitoreoXFuenteSirhPort")

public class ConsultarPuntoMonitoreoXFuenteWSBean implements ConsultarPuntoMonitoreoXFuenteWS {


    @WebMethod(operationName = "consultarPuntosMonitoreoXFuente")
    @WebResult(name = "datosPuntosMonitoreoXFuente")
    public OS_ConsultarPuntosMonitoreoXFuente consultarPuntosMonitoreoXFuente(OE_ConsultarPuntosMonitoreoXFuente oe) {
       
        UsuarioAguaWSDelegate usu;
        OS_ConsultarPuntosMonitoreoXFuente os = new OS_ConsultarPuntosMonitoreoXFuente();

        try {
           ValidarEntrada(oe);
            usu = UsuarioAguaWSDelegate.getInstance();
           List<DatosReporteWS> listPtos=  usu.consultarDatosXFuente(oe.getCodAutoridadAmbiental(), "PTO");
           if (listPtos != null) {
                os.setCodigoError(0);
                os.setMensajeError("Operación correcta"); 
            os.setDatosPuntosMonitoreoFuente(listPtos);
            }

        } catch (IdeamException e) {

            os.setCodigoError(9999);
            os.setMensajeError("Se genero error :" + e.getMessage());
          }catch (IdeamWsException ex) {
            os.setCodigoError(ex.getCodigo());
            os.setMensajeError(ex.getMensaje());
          }

        return os;
    }
   private void ValidarEntrada(OE_ConsultarPuntosMonitoreoXFuente oe) throws IdeamWsException {
     if (oe.getCodAutoridadAmbiental() == null ||
         oe.getCodAutoridadAmbiental().length() == 0)
         throw new IdeamWsException(1, "codAutoridadAmbiental es requerido");
   }
 }