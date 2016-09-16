package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import java.util.ArrayList;

public class TarifasAcueductoValidator extends AbstractValidator{
    public TarifasAcueductoValidator() {
        
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO id = new ColumnTO("ID", 0);
        this.columnas.add( id );

        ColumnTO nombre = new ColumnTO("Nombre", 1);
        nombre.addValidator(new Obligatorio());
        this.columnas.add( nombre );
        
        ColumnTO agno = new ColumnTO("Año", 2);
        agno.addValidator( new Obligatorio() );
        agno.addValidator( new LongValidator() );
        agno.addValidator( new LongRangeValidator(1900L, 2100L));
        this.columnas.add( agno);
        
        ColumnTO servicio = new ColumnTO("Servicio", 3);
        this.columnas.add( servicio );
        
        ColumnTO secuencia = new ColumnTO("Car secuencia", 4);
        this.columnas.add( secuencia );
        
        ColumnTO codDane = new ColumnTO("Codigo DANE", 5);
        codDane.addValidator(new Obligatorio());
        codDane.addValidator( new LongValidator() );
        this.columnas.add( codDane );
        
        ColumnTO codDepto = new ColumnTO("Codigo departamento", 6);
        codDepto.addValidator(new Obligatorio());
        codDepto.addValidator( new LongValidator() );
        this.columnas.add( codDepto );
        
        ColumnTO depto = new ColumnTO("Departamento", 7);
        depto.addValidator(new Obligatorio());
        depto.addValidator( new DepartamentoValidator() );
        this.columnas.add( depto );
        
        ColumnTO codMun = new ColumnTO("Codigo municipio", 8);
        codMun.addValidator(new Obligatorio());
        codMun.addValidator( new LongValidator() );
        this.columnas.add( codMun );
        
        ColumnTO mun = new ColumnTO("Municipio", 9);
        mun.addValidator(new Obligatorio());
        mun.addValidator( new MunicipioValidator(depto.getTitulo()) );   
        this.columnas.add( mun );
        
        ColumnTO ubicacion = new ColumnTO("Ubicacion", 10);
        ubicacion.addValidator(new Obligatorio());
        ubicacion.addValidator( new LongValidator() );
        this.columnas.add( ubicacion );
        
        ColumnTO mes = new ColumnTO("Mes", 11);
        mes.addValidator(new Obligatorio());
        mes.addValidator( new LongValidator() );
        this.columnas.add( mes );
        
        ColumnTO uso = new ColumnTO("Clase uso", 12);
        uso.addValidator(new Obligatorio());
        this.columnas.add( uso );
        
        ColumnTO estrato = new ColumnTO("Estrato", 13);
        estrato.addValidator(new Obligatorio());
        estrato.addValidator( new LongValidator() );
        //estrato.addValidator( new LongRangeValidator(1L, 12L));
        //estrato.addValidator(new ListasValidator(72L, "Estrato Socio Economico"));
        this.columnas.add( estrato );
        
        ColumnTO cargoFijo = new ColumnTO("Cargo fijo", 14);
        //cargoFijo.addValidator(new Obligatorio());
        cargoFijo.addValidator( new DoubleValidator() );
        this.columnas.add( cargoFijo );
        
        ColumnTO cargoBasico = new ColumnTO("Cargo basico", 15);
       // cargoBasico.addValidator(new Obligatorio());
       //   cargoBasico.addValidator( new DoubleValidator() );
            this.columnas.add( cargoBasico );
        
        ColumnTO cargoComplementario = new ColumnTO("Cargo complementario", 16);
        //cargoComplementario.addValidator(new Obligatorio());
        // cargoComplementario.addValidator( new DoubleValidator() );
        this.columnas.add( cargoComplementario );
        
        ColumnTO cargoSuntuario = new ColumnTO("Cargo suntuario", 17);
        //cargoSuntuario.addValidator(new Obligatorio());
        //cargoSuntuario.addValidator( new DoubleValidator() );
        this.columnas.add( cargoSuntuario );

        
        
        ColumnTO tarifaPlena = new ColumnTO("Tarifa Plena", 18);
        //tarifaPlena.addValidator(new Obligatorio());
        //tarifaPlena.addValidator( new DoubleValidator() );
        this.columnas.add( tarifaPlena );
        
        ColumnTO consumo = new ColumnTO("Tarifa Consumo", 19);
        //tarifaPlena.addValidator(new Obligatorio());
        //tarifaPlena.addValidator( new DoubleValidator() );
        this.columnas.add( consumo );
        

    }
}
