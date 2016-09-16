package co.gov.ideam.sirh.webservices.businessl.consultarCaptacionXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarCaptacionXFuente.OE_ConsultarCaptacionXFuente;
import co.gov.ideam.sirh.webservices.model.consultarCaptacionXFuente.OS_ConsultarCaptacionXFuente;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarCaptacionXFuenteSirhWS",
           mappedName = "Sirh-ConsultarCaptacionXFuenteWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarCaptacionXFuente",
            serviceName = "WS_SIAC_SIRH_ConsultarCaptacionXFuente",
            portName = "ConsultarCaptacionXFuenteSirhPort")

public class ConsultarCaptacionXFuenteWSBean implements ConsultarCaptacionXFuenteWS {


    @WebMethod(operationName = "consultarCaptacionXFuente")
    @WebResult(name = "datosCaptacionXFuente")
    public OS_ConsultarCaptacionXFuente consultarCaptacionXFuente(OE_ConsultarCaptacionXFuente oe) {
       
        UsuarioAguaWSDelegate usu;
        OS_ConsultarCaptacionXFuente os = new OS_ConsultarCaptacionXFuente();
       
        try {
            ValidarEntrada(oe);
            usu = UsuarioAguaWSDelegate.getInstance();
            List<DatosReporteWS> listaCap=  usu.consultarDatosXFuente(oe.getCodAutoridadAmbiental(), "CAP");
            
                os.setCodigoError(0);
                os.setMensajeError("Operación correcta"); 
                os.setDatosCaptacionFuente(listaCap);
           
          if (listaCap == null || listaCap.size() == 0) {
              os.setCodigoError(1);
              os.setMensajeError("No retorna listado ");
          }

        }catch (IdeamException e) {
            os.setCodigoError(9999);
            os.setMensajeError("Se genero error :" + e.getMessage());
        }catch (IdeamWsException ex) {
          os.setCodigoError(ex.getCodigo());
          os.setMensajeError(ex.getMensaje());
      }
      
        return os;
    }
    
  

  private void ValidarEntrada(OE_ConsultarCaptacionXFuente oe) throws IdeamWsException {
    if (oe.getCodAutoridadAmbiental() == null ||
        oe.getCodAutoridadAmbiental().length() == 0)
        throw new IdeamWsException(1, "codAutoridadAmbiental es requerido");
  }
}
