package co.gov.ideam.sirh.webservices.businessl.consultarMuestrasXAnio;

import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.CalidadWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarMuestrasXAnio.OE_ConsultarMuestrasXAnio;
import co.gov.ideam.sirh.webservices.model.consultarMuestrasXAnio.OS_ConsultarMuestrasXAnio;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarMuestrasXAnioSirhWS",
           mappedName = "Sirh-ConsultarMuestrasXAnioWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarMuestrasXAnio",
            serviceName = "WS_SIAC_SIRH_ConsultarMuestrasXAnio",
            portName = "ConsultarMuestrasXAnioSirhPort")

public class ConsultarMuestrasXAnioWSBean implements ConsultarMuestrasXAnioWS {


  @WebMethod(operationName = "consultarMuestrasXAnio")
  @WebResult(name = "datosMuestrasXAnio")
  public OS_ConsultarMuestrasXAnio consultarMuestrasXAnio(OE_ConsultarMuestrasXAnio oe) {

    CalidadWSDelegate cld;
    OS_ConsultarMuestrasXAnio os = new OS_ConsultarMuestrasXAnio();

    try {
      ValidarEntrada(oe);
      cld = CalidadWSDelegate.getInstance();
      System.out.println("entreee");
      List<DatosReporteWS> listMst =
        cld.consultarDatosCalidad(oe.getCodAutoridadAmbiental(), "MST");
      if (listMst != null) {
        os.setCodigoError(0);
        os.setMensajeError("Operación correcta");
        os.setDatosMuestrasAnio(listMst);
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

  private void ValidarEntrada(OE_ConsultarMuestrasXAnio oe) throws IdeamWsException {
    if (oe.getCodAutoridadAmbiental() == null ||
        oe.getCodAutoridadAmbiental().length() == 0)
      throw new IdeamWsException(1, "codAutoridadAmbiental es requerido");
  }
}
