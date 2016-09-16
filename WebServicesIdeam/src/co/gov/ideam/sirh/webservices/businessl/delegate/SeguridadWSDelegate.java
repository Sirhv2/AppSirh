package co.gov.ideam.sirh.webservices.businessl.delegate;

import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.seguridad.model.Funcionario;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.ServletLocatorWS;
import java.util.List;

public class SeguridadWSDelegate {

    /**
     * Referencia al EJB de Seguridad
     */
    private static SeguridadEJB seguridadService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static SeguridadWSDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static SeguridadWSDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new SeguridadWSDelegate();
        }
        return instance;
    }
    
    private SeguridadWSDelegate() throws IdeamException {                
        seguridadService = ServletLocatorWS.getSeguridadService();
    }

    public List<Funcionario> consultarFuncionarios(String codAutoridadAmbiental) throws IdeamException {
       return seguridadService.consultarFuncionarios(codAutoridadAmbiental);
     
    }
    
    public int autenticarUser(String login, String clave)throws IdeamException{        
        int codmensaje = seguridadService.autenticarUser(login, clave);
        return codmensaje;
    }
}
