package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ListasOpcionalValidator implements ColumnValidatorInterface, Serializable{
    private String error;
    private String nombreCampo;
    private Long codigoCategoria;
    
    public ListasOpcionalValidator() {
        this.codigoCategoria = 2L;
        this.nombreCampo = "vacio";        
    }
    public ListasOpcionalValidator(Long categoria, String nombreCampo) {
        super();
        this.codigoCategoria = categoria;
        this.nombreCampo = nombreCampo;
    }

    public boolean valid(Object value) {
        //System.out.println("-1 Validator Listas " + codigoCategoria + " " + nombreCampo + " -" + value + "-");
        ParametrosEJB parametrosService = null;
        if(value != null  && value instanceof String && 
                !value.toString().trim().equalsIgnoreCase("")){
            //System.out.println("0 Validator Listas " + codigoCategoria + " " + nombreCampo + " -" + value + "-");
            try{
                parametrosService = this.getParametrosService();
            }catch(IdeamException e){
                error = e.getMessage();
                return false;
            }
            try{
                /*System.out.println("------------------1 Validator Listas " + 
                                   codigoCategoria + ", " + nombreCampo + ", " + 
                                   value.toString().trim().toUpperCase());
                */
                Lista lista = 
                    parametrosService.
                    getListaByDescripcion(value.toString().trim().toUpperCase(), 
                                                            codigoCategoria);
                if(lista==null){
                    //System.out.println("------------------1 NULO");
                    error = "No Existe " + nombreCampo +" con esta descripcion: "+ 
                            value.toString().trim().toUpperCase();
                    return false;                                    
                }
            }catch(IdeamException e){
                
                //System.out.println("------------------1 EXCEPCION");
                error = "No Existe " + nombreCampo + " con esta descripcion: "+ 
                        value.toString().trim().toUpperCase();
                return false;                
            }
            return true;
        }else{
            //error = "No Existe " + nombreCampo + " con esta descripcion";
            //return false;
            return true;
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
