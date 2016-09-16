package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ColumnValidatorInterface;

import co.gov.ideam.sirh.archivos.model.validator.CompositeValidatorInterface;
import co.gov.ideam.sirh.archivos.model.validator.RequiresAutoridadValidatorInterface;
import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CellTO extends ColumnTO implements Serializable{
    private Object value;
    private boolean valido;
    private String detalleError;
    
    public CellTO(String titulo, int indice) {
        super(titulo, indice);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getDetalleError() {
        return detalleError;
    }

    public void setDetalleError(String detalleError) {
        this.detalleError = detalleError;
    }
    
    public void validar(Autoridades autoridad, List previousValues){        
        detalleError = "";
        boolean valido = true;        
        Iterator it = getValidadores().iterator();
        while(it.hasNext()){            
            ColumnValidatorInterface validator = (ColumnValidatorInterface)it.next();            
            if(validator instanceof RequiresAutoridadValidatorInterface){
                ((RequiresAutoridadValidatorInterface)validator).setAutoridad(autoridad);
            }
            if(validator instanceof CompositeValidatorInterface){
                ((CompositeValidatorInterface)validator).setValues(previousValues);                
            }
            valido = validator.valid(value);                
            //System.out.println("Validando " + validator.getClass().getName() + " " + value.toString() + " resultado " + valido);
            if (!valido){
                detalleError += value.toString() + " ->" + validator.getMensajeError() + "\n";            
            }
        }
        this.setValido( valido );
    }    
}
