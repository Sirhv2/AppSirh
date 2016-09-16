package co.gov.ideam.sirh.archivos.model.validator;


import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.util.IdeamException;


import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PuntoNoExisteValidator implements RequiresAutoridadValidatorInterface, Serializable{
        
        private String error;        
        private Autoridades autoridad;
        
        public PuntoNoExisteValidator() {
            super();
        }

        public Autoridades getAutoridad() {
            return this.autoridad;
        }

        public void setAutoridad(Autoridades autoridad) {
            this.autoridad = autoridad;
        }

        public boolean valid(Object value) {
            CalidadEJB calidadService = null;
            if(value != null &&
                value instanceof String){
                try{
                    calidadService = this.getCalidadService();
                }catch(IdeamException e){
                    error = e.getMessage();
                    return false;
                }
                try{
                    
                    System.out.println("value.toString()-------------------------------------  "+value.toString());
                    
                    PuntoMonitoreo existe=  calidadService.getPuntosMonitoreoNomAut(value.toString(),this.getAutoridad().getId().longValue());
                                     
                    if(existe==null){
                        error = "El Punto de Monitoreo " + value + " no se encuentra registrado para la autoridad " + this.getAutoridad().getNombre();
                        return false;                                    
                    }
                }catch(IdeamException e){
                    error = "El Punto de Monitoreo no se encuentra registrado para la autoridad";
                    return false;                
                }
                return true;
            }else{
                error = "El Punto de Monitoreo no se encuentra registrado para la autoridad";
                return false;
            }
        }

        public String getMensajeError() {
            return error;
        }
        
        private CalidadEJB getCalidadService()throws IdeamException{
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/CalidadEJB");        
                return (CalidadEJB)obj;
            } catch (NamingException e) {
                throw new IdeamException("Imposible ubicar el EJB de Calidad " + 
                                         e.getMessage());
            }        
        }    
    }
