package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;

import java.io.Serializable;

import java.util.List;

public interface CompositeValidatorInterface extends ColumnValidatorInterface{
    
    public void setValues(List<CellTO> campos);
}
