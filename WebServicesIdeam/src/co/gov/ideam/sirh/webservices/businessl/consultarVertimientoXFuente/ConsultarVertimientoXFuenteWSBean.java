package co.gov.ideam.sirh.webservices.businessl.consultarVertimientoXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarVertimientoXFuente.OE_ConsultarVertimientoXFuente;
import co.gov.ideam.sirh.webservices.model.consultarVertimientoXFuente.OS_ConsultarVertimientoXFuente;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarVertimientoXFuenteSirhWS",
           mappedName = "Sirh-ConsultarVertimientoXFuenteWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarVertimientoXFuente",
            serviceName = "WS_SIAC_SIRH_ConsultarVertimientoXFuente",
            portName = "ConsultarVertimientoXFuenteSirhPort")

public class ConsultarVertimientoXFuenteWSBean implements ConsultarVertimientoXFuenteWS {


    @WebMethod(operationName = "consultarVertimientoXFuente")
    @WebResult(name = "datosVertimientoXFuente")
    public OS_ConsultarVertimientoXFuente consultarVertimientoXFuente(OE_ConsultarVertimientoXFuente oe) {
       
        UsuarioAguaWSDelegate usu;
        OS_ConsultarVertimientoXFuente os = new OS_ConsultarVertimientoXFuente();

        try {
            ValidarEntrada(oe);
            usu = UsuarioAguaWSDelegate.getInstance();
            List<DatosReporteWS> listaVert=  usu.consultarDatosXFuente(oe.getCodAutoridadAmbiental(),"VRT");
           if(listaVert!=null){
                os.setCodigoError(0);
                os.setMensajeError("Operación correcta"); 
                os.setDatosVertimientosFuente(listaVert);
            }

        } catch (IdeamException e) {

            os.setCodigoError(9999);
            os.setMensajeError("Se genero error :" + e.getMessage());
          } catch (IdeamWsException ex) {
            os.setCodigoError(ex.getCodigo());
            os.setMensajeError(ex.getMensaje());
          }

        return os;
    }
    
   private void ValidarEntrada(OE_ConsultarVertimientoXFuente oe) throws IdeamWsException {
     if (oe.getCodAutoridadAmbiental() == null ||
         oe.getCodAutoridadAmbiental().length() == 0)
       throw new IdeamWsException(1, "codAutoridadAmbiental es requerido");
   }
 }