package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasDiagnosticoResiduosValidator extends AbstractValidator{
    public FuniasDiagnosticoResiduosValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        ColumnTO fuente = new ColumnTO("Residuos Solidos", 1);
        fuente.addValidator(new Obligatorio());
        this.columnas.add( fuente );
    }
}
