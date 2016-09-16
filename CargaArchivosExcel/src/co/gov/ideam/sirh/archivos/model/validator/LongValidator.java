package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

public class LongValidator implements ColumnValidatorInterface, Serializable{
    
    private String error;
    
    public boolean valid(Object value) {
        try{
            if(value instanceof Double){
                // validar que no tenga decimales
                Double valor = (Double)value;
                if(valor.longValue() != valor.doubleValue()){
                    error = "No se permiten números decimales";
                    return false;
                }
            }else if(value instanceof String){      
                // Se convierte a double porque se cargan valores con decimales .0
                // desde excel y estos generan errores de conversion a Long
                Double doubleVal = Double.parseDouble((String)value);
                Long valor = doubleVal.longValue();
            }else{
                error = "Tipo de dato incorrecto " + value.getClass().getName();
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            error = "Solo se permiten caracteres numéricos ->" + e.getMessage() +"<->" + value.getClass().getName();
            return false;
        }
    }

    public String getMensajeError() {
        return error;
    }    
}
