package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasGeologiaValidator extends AbstractValidator{

    public FuniasGeologiaValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        ColumnTO localizacion = new ColumnTO("Localizacion Topografica", 1);
        localizacion.addValidator(new Obligatorio());
        localizacion.addValidator(new ListasValidator(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_LOCALIZACION_TOPOGRAFICA,
                                                      "Localizacion Topografica"));
        this.columnas.add( localizacion );
        
        ColumnTO geoforma = new ColumnTO("Geoforma", 2);
        geoforma.addValidator(new Obligatorio());
        geoforma.addValidator(new ListasValidator(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_GEOFORMA, 
                                                  "Geoforma"));
        this.columnas.add( geoforma );
        
    }
}
