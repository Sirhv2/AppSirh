package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PredioCedulaValidator implements ColumnValidatorInterface, Serializable{
    private String error;    
    
    public PredioCedulaValidator() {
        super();
    }

    public boolean valid(Object value) {
        UsuariosAguaEJB usuariosService = null;
        if(value != null &&
            value instanceof String){
            try{
                usuariosService = this.getUsuariosService();
            }catch(IdeamException e){
                error = e.getMessage();
                return false;
            }
            try{
                Predio predio = usuariosService.getPredio(value.toString());
                
                if(predio!=null){
                    error = "Ya existe un predio con esta cédula catastral";
                    return false;                                    
                }
            }catch(IdeamException e){
                error = "Error consultando cedula de predio " + e.getMessage();
                return false;                
            }
            return true;
        }else{
            error = "Ya existe un predio con esta cédula catastral";;
            return false;
        }
    }

    public String getMensajeError() {
        return this.error;
    }
    
    private UsuariosAguaEJB getUsuariosService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/UsuariosAgua");        
            return (UsuariosAguaEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Usuarios del Agua " + 
                                     e.getMessage());
        }        
    }
}
