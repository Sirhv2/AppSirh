package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MunicipioValidator implements CompositeValidatorInterface, Serializable {
    private String error;    
    private List<CellTO> campos;
    private String columnaPadre;
    
    public MunicipioValidator(){
        super();
    }
    public MunicipioValidator(String columnaPadre) {
        super();
        this.columnaPadre = columnaPadre;
    }

    public boolean valid(Object value) {
        if(this.campos !=null && columnaPadre!=null){
            String nombreDepartamento = "";  
            System.out.println("columnaPadre:"+columnaPadre);
            Iterator it = this.campos.iterator();
            while(it.hasNext()){
                CellTO celda = (CellTO)it.next();
                if(celda.getTitulo().equalsIgnoreCase(columnaPadre)){
                    nombreDepartamento = celda.getValue().toString();
                    break;                    
                }                
            }
            System.out.println("Municipio validator nombreDepartamento:"+nombreDepartamento);
            if( nombreDepartamento!= null && nombreDepartamento.length()>0){
                ParametrosEJB parametrosService = null;               
                try{
                    parametrosService = this.getParametrosService();
                }catch(IdeamException e){
                    error = e.getMessage();
                    return false;
                }
                
                try{
                    // Validar el departamento
                    Divipola depto = parametrosService.getDivipolaByName(nombreDepartamento.trim(),
                                                                         Divipola.CLASE_DEPTO);
                    if(depto==null){
                        error = "No Existe un Departamento con esta descripcion";
                        return false;                                    
                    }
                    System.out.println("Validando desde compiste " + depto.getNombre() + " " + depto.getId());
                    // Buscar el municipio con el nombre y asociado al depto
                    
                    System.out.println("Validando Municipio----------------------------- " + value.toString() + " " + depto.getId());
                    
                    Divipola mun = parametrosService.getDivipolaByName(value.toString(),
                                                                       depto);
                    if(mun==null){
                        error = "No Existe un Municipio con nombre " + value.toString() + " asociado al Departamento " + depto.getNombre();
                        return false;                                    
                    }        
                    return true;                
                }catch(IdeamException e){
                    error = "No Existe un Departamento con esta descripcion";
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

