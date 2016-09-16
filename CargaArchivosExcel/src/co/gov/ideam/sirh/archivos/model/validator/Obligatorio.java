package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

public class Obligatorio implements ColumnValidatorInterface, Serializable{
    
    private String error;
    public Obligatorio() {
        super();
    }

    public boolean valid(Object value) {
        if(value==null){
            error = "Campo Obligatorio";
            return false;
        }else if(value instanceof String){
            if (value.toString().length()==0){
                error = "Campo Obligatorio";
                return false;                
            }
        }
        return true;
    }

    public String getMensajeError() {
        return error;
    }
}
