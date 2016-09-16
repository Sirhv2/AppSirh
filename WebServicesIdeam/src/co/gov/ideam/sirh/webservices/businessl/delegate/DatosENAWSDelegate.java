package co.gov.ideam.sirh.webservices.businessl.delegate;


import co.gov.ideam.sirh.datosena.business.DatosEnaEJB;
import co.gov.ideam.sirh.datosena.model.DatosAreaPomcas;
import co.gov.ideam.sirh.datosena.model.DatosAvanceSIRH;
import co.gov.ideam.sirh.datosena.model.DatosCargaContaminante;
import co.gov.ideam.sirh.datosena.model.DatosCuerposLenticosTO;
import co.gov.ideam.sirh.datosena.model.DatosOfertaAreaTO;
import co.gov.ideam.sirh.datosena.model.DatosSIRH;
import co.gov.ideam.sirh.datosena.model.DatosSIRHAutoridad;
import co.gov.ideam.sirh.datosena.model.DatosSIRHDep;
import co.gov.ideam.sirh.datosena.model.DatosSectoresArea;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.ServletLocatorWS;
import java.util.List;



public class DatosENAWSDelegate {

    /**
     * Referencia al EJB de Seguridad
     */
    private static  DatosEnaEJB datosenaService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static DatosENAWSDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static DatosENAWSDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new DatosENAWSDelegate();
        }
        return instance;
    }
    
    private DatosENAWSDelegate() throws IdeamException {                
        datosenaService = ServletLocatorWS.getDatosENAService();
    }

    public List<DatosOfertaAreaTO> consultarOfertaArea ( ) throws IdeamException{
       return datosenaService.consultarOfertaArea();
    }
    
    public List<DatosCuerposLenticosTO> consultarCuerposLenticos() throws IdeamException{
        return datosenaService.consultarCuerposLenticos();
    }
    
    public List<DatosSectoresArea> consultarSectoresArea() throws IdeamException{
        return datosenaService.consultarSectoresArea();
    }
    
    public List<DatosAreaPomcas> consultarDatosAreaPomcas() throws IdeamException{
        return datosenaService.consultarDatosAreaPomcas();
    }
    
    public List<DatosCargaContaminante> consultarDatosCargaContaminante() throws IdeamException{
        return datosenaService.consultarDatosCargaContaminante();
    }
  
    public List <DatosSIRH> consultarDatosSIRH() throws IdeamException{
        return datosenaService.consultarDatosSIRH();
    }
    
    public List <DatosSIRHDep> consultarDatosSIRHDep() throws IdeamException{
        return datosenaService.consultarDatosSIRHDep();
    }
    public List <DatosAvanceSIRH> consultarDatosAvanceSIRH() throws IdeamException {
    return datosenaService.consultarDatosAvanceSIRH();
    }
    
    public List <DatosSIRHAutoridad> consultarDatosSIRHAutoridad(String sigla) throws IdeamException {
        return datosenaService.consultarDatosSIRHAutoridad(sigla);
        }
  
}


 