package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CaptacionResolucionValidator implements ColumnValidatorInterface, Serializable{
    private String error;    
    
    public CaptacionResolucionValidator() {
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
                Concesion concesion = usuariosService.getConcesionByResolucion(
                                          value.toString());
                
                if(concesion!=null){
                    error = "Ya existe una concesion con este numero de resolucion";
                    return false;                                    
                }
            }catch(IdeamException e){
                error = "Error consultando resolucion de la concesion " + e.getMessage();
                return false;                
            }
            return true;
        }else{
            error = "Ya existe una concesion con este numero de resolucion";
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

