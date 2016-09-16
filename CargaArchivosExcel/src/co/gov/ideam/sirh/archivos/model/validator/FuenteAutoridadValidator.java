package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FuenteAutoridadValidator implements RequiresAutoridadValidatorInterface, Serializable{
    
    private String error;        
    private Autoridades autoridad;
    
    public FuenteAutoridadValidator() {
        super();
    }

    public Autoridades getAutoridad() {
        return this.autoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public boolean valid(Object value) {
        FuentesEJB fuenteService = null;
        if(value != null &&
            value instanceof String){
            try{
                fuenteService = this.getFuentesService();
            }catch(IdeamException e){
                error = e.getMessage();
                return false;
            }
            try{
                FnttFuente existe=  fuenteService.getFuente(value.toString(), this.getAutoridad());
                if(existe!=null){
                    error = "La fuente " + value + " se encuentra registrada para la autoridad " + this.getAutoridad().getNombre();
                    return false;                                    
                }
            }catch(IdeamException e){
                error = "Esta fuente ya se encuentra registrada para la autoridad";
                return false;                
            }
            return true;
        }else{
            error = "Esta fuente ya se encuentra registrada para la autoridad";
            return false;
        }
    }

    public String getMensajeError() {
        return error;
    }
    
    private FuentesEJB getFuentesService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/FuentesEJB");        
            return (FuentesEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("FuenteAutoridadValidator Imposible ubicar el EJB de Fuentes " + 
                                     e.getMessage());
        }        
    }    
}
