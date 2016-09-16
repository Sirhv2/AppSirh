package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;

public interface RequiresAutoridadValidatorInterface extends ColumnValidatorInterface, Serializable{
    
    public Autoridades getAutoridad();
    
    public void setAutoridad(Autoridades autoridad);
}
