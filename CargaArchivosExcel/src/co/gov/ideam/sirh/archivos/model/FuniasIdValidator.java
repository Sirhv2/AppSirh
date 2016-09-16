package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import java.util.ArrayList;

public class FuniasIdValidator extends AbstractValidator{

    public FuniasIdValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO id = new ColumnTO("Identificador Punto", 0);
        id.addValidator(new Obligatorio());
        this.columnas.add( id );

        ColumnTO tipo = new ColumnTO("Tipo Punto", 1);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(19L, "Tipo Punto"));
        this.columnas.add( tipo );
        
    }
}
