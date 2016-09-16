package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasOpcionalValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasConstruccionesAdicionalesValidator extends AbstractValidator{
    public FuniasConstruccionesAdicionalesValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        
        ColumnTO tipo = new ColumnTO("Tipo Construccion", 1);
        tipo.addValidator(new Obligatorio());
        this.columnas.add( tipo );
        
        ColumnTO capacidad = new ColumnTO("Capacidad", 6);
        capacidad.addValidator(new Obligatorio());
        this.columnas.add( capacidad );
        
        
    }
}
