package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

public class LongRangeValidator implements ColumnValidatorInterface, Serializable{
    private Long valorInicial;
    private Long valorFinal;
    private String error;
    
    public LongRangeValidator(){
    }
    
    public LongRangeValidator(Long valorInicial, Long valorFinal){
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }
    
    public boolean valid(Object value) {        
        Long valor = 0L;
        try{            
            if(value instanceof String){
                Double doubleVal = Double.parseDouble((String)value);            
                valor = doubleVal.longValue();
            }else if(value instanceof Long){                
                valor = (Long)value;
            }else{
                error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "] ";
                return false;                
            }
        }catch(NumberFormatException e){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "] ";
            return false;
        }
        if(valor==null){
            return true;
        }
        if(valorInicial == null || valorFinal==null){
            return true;
        }
        if(valor.longValue() < valorInicial.longValue()){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "] ";
            return false;            
        }
        if(valor.longValue() > valorFinal.longValue()){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "] ";
            return false;            
        }
        
        return true;
    }

    public String getMensajeError() {
        return error;
    }
}