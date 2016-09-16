package co.gov.ideam.sirh.datossinc.view;

import co.gov.ideam.sirh.datossinc.business.DatosSincEJB;
import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;
import java.util.List;

public class DatosSincDelegate {

    /**
     * Referencia al EJB
     */
    private static DatosSincEJB datosSincService = null;
    /**
    * Utilizada para implementar singleton
    */
    private static DatosSincDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    
     /**
     * Construcutor privado para implementar singleton
     */
     private DatosSincDelegate(){
         datosSincService = ServletLocator.getDatosSincService();
     }
    public static DatosSincDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new DatosSincDelegate();
        }
        return instance;
    }

    
    public List consultarDatosRUA() throws IdeamException {
       return datosSincService.consultarDatosRUA();
    }
    
    public List consultarDatosRUAxAut(Long idAutoridad) throws IdeamException {
          return datosSincService.consultarDatosRUAxAut(idAutoridad);
    }
    
    
    public UsuarioAguaSinc consultarUsuarioRua(Long codigoUsuario) throws IdeamException{
          return datosSincService.consultarUsuarioRua(codigoUsuario);
    }

    public RepresentanteSinc consultarRepresentanteRua(Long codigoUsuario) throws IdeamException{
          return datosSincService.consultarRepresentanteRua(codigoUsuario);
    }

    public PredioSinc consultarPredioRua(Long codigoPredio) throws IdeamException{
          return datosSincService.consultarPredioRua(codigoPredio);
    }

    public ConcesionSinc consultarConcesionRua(Long codigoConcesion) throws IdeamException{
          return datosSincService.consultarConcesionRua(codigoConcesion);
    }

    public PermisoVertimientoSinc consultarPermisoVertimientoRua(Long codigoPV) throws IdeamException{
          return datosSincService.consultarPermisoVertimientoRua(codigoPV);
    }
   
    
    
   
    
}



