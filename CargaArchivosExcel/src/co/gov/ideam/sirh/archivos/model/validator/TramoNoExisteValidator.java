package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class TramoNoExisteValidator implements CompositeValidatorInterface , RequiresAutoridadValidatorInterface, Serializable{
     
        private String error;    
        private List<CellTO> campos;
        private String columnaPadre;
        private Autoridades autoridad;
    
    
    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }
  
    public TramoNoExisteValidator() {
             super();
           
         }

   public TramoNoExisteValidator(String columnaPadre) {
            super();
            this.columnaPadre = columnaPadre;
            
        }

        public boolean valid(Object value) {
            if(this.campos !=null && columnaPadre!=null){
                String nombreFuente = "";        
                Iterator it = this.campos.iterator();
                while(it.hasNext()){
                    CellTO celda = (CellTO)it.next();
                    if(celda.getTitulo().equalsIgnoreCase(columnaPadre)){
                        nombreFuente = celda.getValue().toString();
                        break;                    
                    }                
                }
                
                if( nombreFuente!= null && nombreFuente.length()>0 ){
                  FuentesEJB fuenteService = null;             
                    try{
                        fuenteService = this.getFuentesService();
                     }catch(IdeamException e){
                            error = e.getMessage();
                            return false;
                        }
                    try{
                        // Validar fuente 
                    
                        FnttFuente fuente=  fuenteService.getFuente(nombreFuente.toUpperCase().trim(), 
                                                                    this.getAutoridad());
                        if(fuente==null){
                                error = "El Tramo  " + value + " no se encuentra registrado para la autoridad " + this.getAutoridad().getNombre();
                                return false;   
                        }

                        
                        // Buscar el tramo con el nombre y asociado a l fuente y autoridad ambiental
                        if(fuente!=null){
                   
                        FnttTramo existe=  fuenteService.getTramo(value.toString().toUpperCase().trim(),
                                                                  fuente , 
                                                                  this.getAutoridad());
                         if(existe==null){
                            error = "El Tramo  " + value + " no se encuentra registrado para la autoridad " + this.getAutoridad().getNombre();
                            return false;                                    
                                  
                        }  
                         
                        return true;    
                        }
                    }catch(IdeamException e){
                        error = "Este Tramo no se encuentra registrado para la autoridad";
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
        
       

        public void setValues(List<CellTO> campos) {
            this.campos = campos;
        }
       


    private FuentesEJB getFuentesService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/FuentesEJB");        
            return (FuentesEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("TramoNoExisteValidator Imposible ubicar el EJB de Fuentes " + 
                                     e.getMessage());
        }        
    }

  
}

