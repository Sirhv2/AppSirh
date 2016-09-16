package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import java.util.ArrayList;

public class PoblacionValidator extends AbstractValidator{
    public PoblacionValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO id = new ColumnTO("ID FUENTE", 0);
        this.columnas.add( id );

        ColumnTO tipo = new ColumnTO("Tipo", 1);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipo );

        ColumnTO nombre = new ColumnTO("Nombre", 2);
        nombre.addValidator(new Obligatorio());
        //nombre.addValidator( new FuenteTipoValidator(tipo.getTitulo(), 12L) );
        this.columnas.add( nombre );
        
        ColumnTO flujo = new ColumnTO("Flujo", 10);
        flujo.addValidator( new Obligatorio() );
        flujo.addValidator( new ListasValidator(13L, "Tipo Flujo") );
        this.columnas.add( flujo );
        
        ColumnTO gradosLatitudInicial = new ColumnTO("Grados Latitud Inicial", 18);
        gradosLatitudInicial.addValidator( new Obligatorio() );
        gradosLatitudInicial.addValidator( new LongValidator() );
        gradosLatitudInicial.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitudInicial);
        
        ColumnTO depto = new ColumnTO("Departamento",5);        
        depto.addValidator( new Obligatorio() );        
        depto.addValidator( new DepartamentoValidator() );
        this.columnas.add( depto );
        
        ColumnTO municipio = new ColumnTO("Municipio",6);
        municipio.addValidator( new Obligatorio() );        
        municipio.addValidator( new MunicipioValidator(depto.getTitulo()) );        
        this.columnas.add( municipio );

    }
}
