package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasDiagnosticoValidator extends AbstractValidator{
    public FuniasDiagnosticoValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );
        
        ColumnTO letrina = new ColumnTO("Letrina Cercana", 1);
        this.columnas.add( letrina );
        
        ColumnTO distanciaLetrina = new ColumnTO("Distancia Letrina", 2);
        this.columnas.add( distanciaLetrina );
        
        ColumnTO charco = new ColumnTO("Charco Agua Estancada", 3);
        this.columnas.add( charco );
        
        ColumnTO distanciaCharco = new ColumnTO("Distancia Charco", 4);
        this.columnas.add( distanciaCharco );
        
        ColumnTO basura = new ColumnTO("Basura, Criaderos, Estiercol Alrededor", 5);
        this.columnas.add( basura );
        
        ColumnTO distanciaBasura = new ColumnTO("Distancia Basura, Criaderos, Estiercol Alrededor", 6);
        this.columnas.add( distanciaBasura );
        
        ColumnTO borde = new ColumnTO("Borde o Grieta de Filtración Superficial", 7);
        this.columnas.add( borde );
        
        ColumnTO distanciaBorde = new ColumnTO("Distancia Borde o Grieta de Filtración Superficial", 8);
        this.columnas.add( distanciaBorde );
        
        ColumnTO cubierta = new ColumnTO("Cubierta Adecuada", 9);
        this.columnas.add( cubierta );
        
        ColumnTO sello = new ColumnTO("Sello Sanitario", 10);
        this.columnas.add( sello );
        
        ColumnTO piso = new ColumnTO("Piso Cemento", 11);
        this.columnas.add( piso );
        
        ColumnTO cerco = new ColumnTO("Cerco Instalado", 12);
        this.columnas.add( cerco );
        
        
    }
}
