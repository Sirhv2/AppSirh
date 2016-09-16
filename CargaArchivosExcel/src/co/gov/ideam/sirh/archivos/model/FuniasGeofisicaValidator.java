package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasOpcionalValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasGeofisicaValidator extends AbstractValidator{
    public FuniasGeofisicaValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        ColumnTO fecha = new ColumnTO("Fecha Medicion", 1);
        fecha.addValidator(new Obligatorio());
        this.columnas.add( fecha );
        
        ColumnTO equipo = new ColumnTO("Equipo Usado", 2);
        this.columnas.add( equipo );
        
        ColumnTO tipoRegistro = new ColumnTO("Tipo Registro", 3);
        tipoRegistro.addValidator( new ListasOpcionalValidator(ConstantesParametros.
                                                         CATEGORIA_TIPO_REGISTRO, 
                                                         "Tipo Registro") );
        this.columnas.add( tipoRegistro );
        
        ColumnTO compania = new ColumnTO("Compania Ejecutora", 4);
        this.columnas.add( compania );
        
        ColumnTO resistividad = new ColumnTO("Resistividad Lodo", 5);
        this.columnas.add( resistividad );
        
        ColumnTO temperatura = new ColumnTO("Temperatura", 6);
        this.columnas.add( temperatura );
        
        ColumnTO profundidad = new ColumnTO("Profundidad Registro", 7);
        this.columnas.add( profundidad );
        
        ColumnTO calidad = new ColumnTO("Calidad Registro", 8);
        this.columnas.add( calidad );
        
    }
}
