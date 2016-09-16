package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasUnidadGeologicaValidator extends AbstractValidator{
    public FuniasUnidadGeologicaValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        ColumnTO unidad = new ColumnTO("Unidad Geologica", 5);
        //unidad.addValidator(new Obligatorio());
        
        this.columnas.add( unidad );

    }
}
