package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;

import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FuenteTipoValidator implements CompositeValidatorInterface, RequiresAutoridadValidatorInterface, Serializable {
    
    private String error; 
    private Long codigoCategoria;
    private List<CellTO> campos;
    private String columnaPadre;
    private Autoridades autoridad;
    
    public FuenteTipoValidator() {
        super();
    }
    
    public FuenteTipoValidator(String columnaPadre, Long categoria) {
        super();
        this.columnaPadre = columnaPadre;
        this.codigoCategoria = categoria;
    }
    
    public Autoridades getAutoridad() {
        return this.autoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }
    
    public boolean valid(Object value) {
        if(this.campos !=null && columnaPadre!=null){
            String nombrePadre = "";  
            Iterator it = this.campos.iterator();
            while(it.hasNext()){
                CellTO celda = (CellTO)it.next();
                if(celda.getTitulo().equalsIgnoreCase(columnaPadre)){
                    nombrePadre = celda.getValue().toString();
                    break;                    
                }                
            }
            if( nombrePadre != null && nombrePadre.length()>0){
                FuentesEJB fuentesService = null; 
                ParametrosEJB parametrosService = null; 
                try{
                    fuentesService = this.getFuentesService();
                    parametrosService = this.getParametrosService();
                }catch(IdeamException e){
                    error = e.getMessage();
                    return false;
                }
                
                try{
                    // Validar el tipo
                    Lista tipo = 
                        parametrosService.getListaByDescripcion(nombrePadre.trim().
                                                                toUpperCase(), 
                                                                codigoCategoria);
                    
                    if(tipo==null){
                        error = "No Existe una lista con esta descripcion";
                        return false;                                    
                    }
                    FnttFuente existe =  
                        fuentesService.getFuente(value.toString().toUpperCase().trim(), 
                                                 this.getAutoridad(), tipo.getCodigo());
                    if(existe!=null){
                        error = "La fuente " + value + " se encuentra registrada para la autoridad " + this.getAutoridad().getNombre();
                        return false;                                    
                    } 
                    return true;                
                }catch(IdeamException e){
                    error = "No se encuentra una lista con esta descripcion";
                    return false;                
                }                
            }
        }       
            error = "No Existen datos de referencia para validar";
            return false;
        
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
            throw new IdeamException("FuenteTipoValidator Imposible ubicar el EJB de Fuentes " + 
                                     e.getMessage());
        }        
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

    public void setValues(List<CellTO> campos) {
        this.campos = campos;
    }
}
