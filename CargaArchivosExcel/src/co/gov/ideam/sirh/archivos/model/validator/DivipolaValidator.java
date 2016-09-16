package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DivipolaValidator implements ColumnValidatorInterface, Serializable{
    private String error;    
    private String claseDivipola;
    private List<Object> values;
    private String columnaPadre;
    
    public DivipolaValidator(){
        this.claseDivipola = "CM";
    }
    public DivipolaValidator(String claseDivipola) {
        super();
        this.claseDivipola = claseDivipola;        
    }
    public DivipolaValidator(String claseDivipola, String columnaPadre) {
        super();
        this.claseDivipola = claseDivipola;
        this.columnaPadre = columnaPadre;
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
                Divipola pola = parametrosService.getDivipolaByName(value.toString(),
                                                                    claseDivipola);
                if(pola==null){
                    error = "No Existe un " + claseDivipola + " con esta descripcion";
                    return false;                                    
                }
            }catch(IdeamException e){
                error = "No Existe un " + claseDivipola + " con esta descripcion";
                return false;                
            }
            return true;
        }else{
            error = "No Existe un " + claseDivipola + " con esta descripcion";
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
