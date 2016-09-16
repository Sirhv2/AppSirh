package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

public class DoubleValidator implements ColumnValidatorInterface, Serializable{
    
    private String error;
    
    public boolean valid(Object value) {
        boolean res=false;
        try{
            if(value instanceof Double){
                res= true;
            }
            if(value instanceof String){
                String tmp=value.toString();
                if(tmp.indexOf(",")!=-1)
                tmp=tmp.replaceAll(",",".");
                Double valor = Double.parseDouble(tmp);
                res= true;
            }else{
                error = "Tipo de dato incorrecto " + value.getClass().getName();
                res= false;
            }
            return res;
        }catch(NumberFormatException e){
            error = "Solo se permiten caracteres numéricos ->" + value.toString() +"<-";
            return false;
        }
    }

    public String getMensajeError() {
        return error;
    }
}
