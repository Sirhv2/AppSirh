package co.gov.ideam.sirh.webservices.businessl.delegate;


import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.ServletLocatorWS;
import java.util.List;



public class CalidadWSDelegate {

    /**
     * Referencia al EJB de Seguridad
     */
    private static  CalidadEJB calidadService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static CalidadWSDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static CalidadWSDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new CalidadWSDelegate();
        }
        return instance;
    }
    
    
    private CalidadWSDelegate() throws IdeamException {                
       calidadService = ServletLocatorWS.getCalidadService();
    }

    //  * Consultar capatacion por fuente  en cada autoridad ambiental 
    public List<DatosReporteWS> consultarDatosCalidad(String codAutoridadAmbiental, String tipo) throws IdeamException {
     return  calidadService.consultarDatosCalidad(codAutoridadAmbiental, tipo);
    
    }
  
}


 