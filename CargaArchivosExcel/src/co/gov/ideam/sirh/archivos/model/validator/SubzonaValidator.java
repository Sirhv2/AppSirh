package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SubzonaValidator implements CompositeValidatorInterface, Serializable {
    
    private String error;    
    private List<CellTO> campos;
    private String columnaPadre;
    
    public SubzonaValidator() {
        super();
    }
    public SubzonaValidator(String columnaPadre) {
        super();
        this.columnaPadre = columnaPadre;
    }
    
    public boolean valid(Object value) {
        if(this.campos !=null && columnaPadre!=null){
            String nombrePadre = "";  
            System.out.println("columnaPadre:"+columnaPadre);
            Iterator it = this.campos.iterator();
            while(it.hasNext()){
                CellTO celda = (CellTO)it.next();
                if(celda.getTitulo().equalsIgnoreCase(columnaPadre)){
                    nombrePadre = celda.getValue().toString();
                    break;                    
                }                
            }
            System.out.println("SubZona validator nombreZona:"+nombrePadre);
            if( nombrePadre != null && nombrePadre.length()>0){
                ParametrosEJB parametrosService = null;               
                try{
                    parametrosService = this.getParametrosService();
                }catch(IdeamException e){
                    error = e.getMessage();
                    return false;
                }
                
                try{
                    // Validar el departamento
                    PartZonificListas zona = 
                        parametrosService.getZonificacionByName(nombrePadre.trim(), null);
                    if(zona==null){
                        error = "No Existe una zona con esta descripcion";
                        return false;                                    
                    }
                    System.out.println("Validando desde composite " + zona.getValor() + " " + zona.getId());
                    // Buscar la zona con el nombre y asociado al depto
                    
                    System.out.println("Validando Subzona----------------------------- " + value.toString() + " " + zona.getId());
                    
                    PartZonificListas subzona = 
                        parametrosService.getZonificacionByName(value.toString(), zona);
                    if(subzona==null){
                        error = "No Existe una subzona con nombre " + value.toString() + " asociada a la zona " + zona.getValor();
                        return false;                                    
                    }        
                    return true;                
                }catch(IdeamException e){
                    error = "No Existe un Zona con esta descripcion";
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

