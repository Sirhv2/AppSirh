package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ColumnValidatorInterface;
import co.gov.ideam.sirh.archivos.model.validator.DoubleRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteAutoridadValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import co.gov.ideam.sirh.archivos.model.validator.SubzonaValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonaValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;

import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.util.ArrayList;

public class FuentesValidator extends AbstractValidator{
    public FuentesValidator() {
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

        ColumnTO existeDB = new ColumnTO("ExisteDB", 3);
        this.columnas.add( existeDB );

        ColumnTO numCar = new ColumnTO("NumCAR",4);
        this.columnas.add( numCar );


        ColumnTO descripcion = new ColumnTO("Descripcion",5);
        descripcion.addValidator(new Obligatorio());
        this.columnas.add( descripcion );

        ColumnTO numCar2 = new ColumnTO("NumCAR",6);
        this.columnas.add( numCar2 );

        ColumnTO nombreTramo = new ColumnTO("Nombre Tramo", 7);
        nombreTramo.addValidator(new Obligatorio());
        this.columnas.add( nombreTramo );

        ColumnTO descripcionTramo = new ColumnTO("Descripcion Tramo",8);
        //descripcionTramo.addValidator(new Obligatorio());
        this.columnas.add( descripcionTramo );

        ColumnTO longitud = new ColumnTO("Longitud", 9);
        longitud.addValidator( new Obligatorio() );
        longitud.addValidator( new DoubleValidator() );
        this.columnas.add( longitud );

        ColumnTO flujo = new ColumnTO("Flujo", 10);
        flujo.addValidator( new Obligatorio() );
        flujo.addValidator( new ListasValidator(13L, "Tipo Flujo") );
        this.columnas.add( flujo );

        ColumnTO area = new ColumnTO("Area", 11);
        area.addValidator( new Obligatorio() );
        area.addValidator( new ZonificacionValidator() );
        this.columnas.add( area );

        ColumnTO zona = new ColumnTO("Zona", 12);
        zona.addValidator( new Obligatorio() );
        zona.addValidator( new ZonificacionValidator() );
        zona.addValidator( new ZonaValidator(area.getTitulo()) );
        this.columnas.add( zona );

        ColumnTO subzona = new ColumnTO("Subzona", 13);
        subzona.addValidator( new Obligatorio() );
        subzona.addValidator( new ZonificacionValidator() );
        subzona.addValidator( new SubzonaValidator(zona.getTitulo()) );
        this.columnas.add( subzona );

        ColumnTO cuenca = new ColumnTO("Cuenca", 14);
        //cuenca.addValidator( new Obligatorio() );
        this.columnas.add( cuenca );

        ColumnTO codigoCuenca = new ColumnTO("Codigo Cuenca", 15);
        //codigoCuenca.addValidator( new Obligatorio() );
        this.columnas.add( codigoCuenca );

        ColumnTO usos = new ColumnTO("Usos", 16);
        this.columnas.add( usos );

        ColumnTO sistemaReferenciaInicial = new ColumnTO("Sistema Referencia Inicial", 17);
        sistemaReferenciaInicial.addValidator( new Obligatorio() );
        sistemaReferenciaInicial.addValidator( new ListasValidator(6L, "Sistema de Referencia Inicial") );
        this.columnas.add( sistemaReferenciaInicial );

        ColumnTO gradosLatitudInicial = new ColumnTO("Grados Latitud Inicial", 18);
        gradosLatitudInicial.addValidator( new Obligatorio() );
        gradosLatitudInicial.addValidator( new LongValidator() );
        gradosLatitudInicial.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitudInicial);

        ColumnTO minutosLatitudInicial = new ColumnTO("Minutos Latitud Inicial", 19);
        minutosLatitudInicial.addValidator( new Obligatorio() );
        minutosLatitudInicial.addValidator( new LongValidator());
        minutosLatitudInicial.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitudInicial );

        ColumnTO segundosLatitudInicial = new ColumnTO("Segundos Latitud Inicial", 20);
        segundosLatitudInicial.addValidator( new Obligatorio() );
        segundosLatitudInicial.addValidator( new DoubleValidator() );
        segundosLatitudInicial.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitudInicial );

        ColumnTO gradosLongitudInicial = new ColumnTO("Grados Longitud Inicial", 21);
        gradosLongitudInicial.addValidator( new Obligatorio() );
        gradosLongitudInicial.addValidator( new LongRangeValidator() );
        gradosLongitudInicial.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitudInicial );

        ColumnTO minutosLongitudInicial = new ColumnTO("Minutos Longitud Inicial", 22);
        minutosLongitudInicial.addValidator( new Obligatorio() );
        minutosLongitudInicial.addValidator( new LongValidator());
        minutosLongitudInicial.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitudInicial );

        ColumnTO segundosLongitudInicial = new ColumnTO("Segundos Longitud Inicial", 23);
        segundosLongitudInicial.addValidator( new Obligatorio() );
        segundosLongitudInicial.addValidator( new DoubleValidator() );
        segundosLongitudInicial.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitudInicial );

        ColumnTO altitudInicial = new ColumnTO("Altitud Inicial", 24);
        altitudInicial.addValidator( new Obligatorio() );
        altitudInicial.addValidator( new DoubleValidator() );
        altitudInicial.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitudInicial );

        ColumnTO sistemaReferenciaFinal = new ColumnTO("Sistema Referencia Final", 25);
        sistemaReferenciaFinal.addValidator( new Obligatorio() );
        sistemaReferenciaFinal.addValidator( new ListasValidator(6L, "Sistema de Referencia Final") );
        this.columnas.add( sistemaReferenciaFinal );

        ColumnTO gradosLatitudFinal = new ColumnTO("Grados Latitud Final", 26);
        gradosLatitudFinal.addValidator( new Obligatorio() );
        gradosLatitudFinal.addValidator( new LongRangeValidator() );
        gradosLatitudFinal.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitudFinal );

        ColumnTO minutosLatitudFinal = new ColumnTO("Minutos Latitud Final", 27);
        minutosLatitudFinal.addValidator( new Obligatorio() );
        minutosLatitudFinal.addValidator( new LongValidator());
        minutosLatitudFinal.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitudFinal );

        ColumnTO segundosLatitudFinal = new ColumnTO("Segundos Latitud Final", 28);
        segundosLatitudFinal.addValidator( new Obligatorio() );
        segundosLatitudFinal.addValidator( new DoubleValidator() );
        segundosLatitudFinal.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitudFinal );

        ColumnTO gradosLongitudFinal = new ColumnTO("Grados Longitud Final", 29);
        gradosLongitudFinal.addValidator( new Obligatorio() );
        gradosLongitudFinal.addValidator( new LongValidator() );
        gradosLongitudFinal.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitudFinal );

        ColumnTO minutosLongitudFinal = new ColumnTO("Minutos Longitud Final", 30);
        minutosLongitudFinal.addValidator( new Obligatorio() );
        minutosLongitudFinal.addValidator( new LongValidator());
        minutosLongitudFinal.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitudFinal );

        ColumnTO segundosLongitudFinal = new ColumnTO("Segundos Longitud Final", 31);
        segundosLongitudFinal.addValidator( new Obligatorio() );
        segundosLongitudFinal.addValidator( new DoubleValidator() );
        segundosLongitudFinal.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitudFinal );

        ColumnTO altitudFinal = new ColumnTO("Altitud Final", 32);
        altitudFinal.addValidator( new Obligatorio() );
        altitudFinal.addValidator( new DoubleValidator() );
        altitudFinal.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitudFinal );

    }
}
