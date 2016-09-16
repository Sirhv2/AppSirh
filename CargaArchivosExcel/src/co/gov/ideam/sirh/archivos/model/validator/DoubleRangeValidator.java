package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

public class DoubleRangeValidator implements ColumnValidatorInterface, Serializable{
    private Double valorInicial;
    private Double valorFinal;
    private String error;
    
    public DoubleRangeValidator(){
        
    }
    public DoubleRangeValidator(Double valorInicial, Double valorFinal){
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }
    
    public boolean valid(Object value) {
        Double valor = 0D;
        try{            
            if(value instanceof String){
                String tmp=value.toString();
                if(tmp.indexOf(",")!=-1)
                tmp=tmp.replaceAll(",",".");
                valor = Double.parseDouble(tmp);
            }else if(value instanceof Double){
                valor = (Double)value;
            }else{
                error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "]";
                return false;                
            }
        }catch(NumberFormatException e){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "]";
            return false;
        }
        if(valor==null){
            return true;
        }        
        if(valor.doubleValue() < valorInicial.doubleValue()){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "]";
            return false;            
        }
        if(valor.doubleValue() > valorFinal.doubleValue()){
            error = "Valor fuera del rango permitido [" + valorInicial + " a " + valorFinal + "]";
            return false;            
        }
        
        return true;
    }

    public String getMensajeError() {
        return error;
    }
}
