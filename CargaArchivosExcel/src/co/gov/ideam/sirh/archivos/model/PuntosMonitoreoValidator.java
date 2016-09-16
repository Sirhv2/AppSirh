package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DivipolaValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteAutoridadValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.archivos.model.validator.TramoCompositeValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;

import co.gov.ideam.sirh.parametros.model.Divipola;

import java.util.ArrayList;

public class PuntosMonitoreoValidator extends AbstractValidator{
    public PuntosMonitoreoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO id = new ColumnTO("ID FUENTE", 0);
        this.columnas.add( id );
             
        ColumnTO nombre = new ColumnTO("Nombre Punto", 1);
        nombre.addValidator(new Obligatorio());        
        this.columnas.add( nombre );
        
        ColumnTO tipo = new ColumnTO("Tipo Punto", 2);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(15L, "Tipo de puntos de monitoreo"));
        this.columnas.add( tipo );
        
        ColumnTO ubicacion = new ColumnTO("Ubicacion",3);
        ubicacion.addValidator(new Obligatorio());
        ubicacion.addValidator(new ListasValidator(16L, "Ubicacion de los puntos de monitoreo"));
        this.columnas.add( ubicacion );

        ColumnTO depto = new ColumnTO("Departamento",4);        
        depto.addValidator( new Obligatorio() );        
        depto.addValidator( new DepartamentoValidator() );
        this.columnas.add( depto );
        
        ColumnTO municipio = new ColumnTO("Municipio",5);
        municipio.addValidator( new Obligatorio() );        
        municipio.addValidator( new MunicipioValidator( depto.getTitulo() ) );        
        this.columnas.add( municipio );
               
        ColumnTO area = new ColumnTO("Area", 6);
        area.addValidator( new Obligatorio() );        
        area.addValidator( new ZonificacionValidator() );
        this.columnas.add( area );
        
        ColumnTO zona = new ColumnTO("Zona", 7);
        zona.addValidator( new Obligatorio() );
        zona.addValidator( new ZonificacionValidator() );
        this.columnas.add( zona );

        ColumnTO subzona = new ColumnTO("Subzona", 8);
        subzona.addValidator( new Obligatorio() );
        subzona.addValidator( new ZonificacionValidator() );
        this.columnas.add( subzona );


        ColumnTO tipof = new ColumnTO("Tipo", 9);
        tipof.addValidator(new Obligatorio());    
        tipof.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipof );

        ColumnTO fuente = new ColumnTO("Fuente", 10);
        fuente.addValidator(new Obligatorio());  
        fuente.addValidator( new FuenteNoExisteValidator(tipof.getTitulo(), 12L) );
        this.columnas.add( fuente );
        
        ColumnTO tramo = new ColumnTO("Tramo", 11);
        tramo.addValidator(new Obligatorio()); 
        tramo.addValidator( new TramoCompositeValidator( tipof.getTitulo(),  12L ,fuente.getTitulo()) );
        this.columnas.add( tramo );

        ColumnTO sistemaReferencia = new ColumnTO("Sistema Referencia", 12);   
        sistemaReferencia.addValidator( new Obligatorio() );
        sistemaReferencia.addValidator( new ListasValidator(6L, "Sistema de referenica geografico") );
        this.columnas.add( sistemaReferencia );
        
        ColumnTO gradosLatitudInicial = new ColumnTO("Grados Latitud", 13);
        gradosLatitudInicial.addValidator( new Obligatorio() );
        gradosLatitudInicial.addValidator( new LongValidator() );
        gradosLatitudInicial.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitudInicial);
        
        ColumnTO minutosLatitudInicial = new ColumnTO("Minutos Latitud", 14);
        minutosLatitudInicial.addValidator( new Obligatorio() );
        minutosLatitudInicial.addValidator( new LongValidator());
        minutosLatitudInicial.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitudInicial );
        
        ColumnTO segundosLatitudInicial = new ColumnTO("Segundos Latitud", 15);
        segundosLatitudInicial.addValidator( new Obligatorio() );
        segundosLatitudInicial.addValidator( new DoubleValidator() );
        segundosLatitudInicial.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitudInicial );
        
        ColumnTO gradosLongitudInicial = new ColumnTO("Grados Longitud", 16);
        gradosLongitudInicial.addValidator( new Obligatorio() );
        gradosLongitudInicial.addValidator( new LongRangeValidator() );
        gradosLongitudInicial.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitudInicial );
        
        ColumnTO minutosLongitudInicial = new ColumnTO("Minutos Longitud", 17);
        minutosLongitudInicial.addValidator( new Obligatorio() );
        minutosLongitudInicial.addValidator( new LongValidator());
        minutosLongitudInicial.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitudInicial );
        
        ColumnTO segundosLongitudInicial = new ColumnTO("Segundos Longitud", 18);
        segundosLongitudInicial.addValidator( new Obligatorio() );
        segundosLongitudInicial.addValidator( new DoubleValidator() );
        segundosLongitudInicial.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitudInicial );

        ColumnTO altitudInicial = new ColumnTO("Altitud", 19);
        altitudInicial.addValidator( new Obligatorio() );
        altitudInicial.addValidator( new DoubleValidator() );
        altitudInicial.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitudInicial );
        
        ColumnTO descripcionAcceso = new ColumnTO("Descripcion de Acceso", 20);
        this.columnas.add( descripcionAcceso );
        
        
        
        
    }
}
