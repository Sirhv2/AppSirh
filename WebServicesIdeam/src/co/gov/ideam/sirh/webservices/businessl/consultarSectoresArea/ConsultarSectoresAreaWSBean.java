package co.gov.ideam.sirh.webservices.businessl.consultarSectoresArea;


import co.gov.ideam.sirh.datosena.model.DatosSectoresArea;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.delegate.DatosENAWSDelegate;
import co.gov.ideam.sirh.webservices.model.consultarSectoresArea.OS_ConsultarSectoresArea;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "ConsultarSectoresAreaSirhWS",
           mappedName = "Sirh-ConsultarSectoresAreaWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_ConsultarSectoresArea",
          serviceName = "WS_SIAC_SIRH_ConsultarSectoresArea",
            portName = "ConsultarOfertaSirhPort")

public class ConsultarSectoresAreaWSBean implements ConsultarSectoresAreaWS {


    @WebMethod(operationName = "consultarSectoresArea")
    @WebResult(name = "datosSectoresArea")
    public OS_ConsultarSectoresArea consultarSectoresArea() {

        
        DatosENAWSDelegate enad;
        OS_ConsultarSectoresArea os = new OS_ConsultarSectoresArea();

        try {

            enad = DatosENAWSDelegate.getInstance();

            List<DatosSectoresArea> listaSectoresArea =
                enad.consultarSectoresArea();

            os.setCodigoError("0");
            os.setMensajeError("Operacion correcta");

            if (listaSectoresArea == null || listaSectoresArea.size() == 0) {
                os.setCodigoError("1");
                os.setMensajeError("No hay registros para el año seleccionado");
            }
            os.setSectoresArea(listaSectoresArea);

        } catch (IdeamException e) {
            os.setCodigoError("9999");
            os.setMensajeError("Se genero error" + e.getMessage());
        }


        return os;
    }
}
