package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ZonificacionValidator implements ColumnValidatorInterface, Serializable{
    private String error;    
    
    public ZonificacionValidator(){
    }
    
    public boolean valid(Object value) {
        ParametrosEJB parametrosService = null;
        if(value != null &&
            value instanceof String){
            try{
                parametrosService = this.getParametrosService();
            }catch(IdeamException e){
                error = e.getMessage();
                return false;
            }
            try{
                PartZonificListas zona = parametrosService.
                    getZonificacionByName(value.toString(), null);
                if(zona==null){
                    error = "No Existe una Zonificacion con esta descripcion";
                    return false;                                    
                }                
            }catch(IdeamException e){
                error = "No Existe una Zonificacion con esta descripcion";
                return false;                
            }
            return true;
        }else{
            error = "No Existe una Zonificacion con esta descripcion";
            return false;
        }
    }

    public String getMensajeError() {
        return error;
    }
    
    private ParametrosEJB getParametrosService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/ParametrosEJB");        
            return (ParametrosEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Parametros " + 
                                     e.getMessage());
        }        
    }
}
