package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.CaptacionResolucionValidator;
import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DivipolaValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;

import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import co.gov.ideam.sirh.archivos.model.validator.PredioCedulaValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoCompositeValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;
import java.util.List;

public class UsuariosAguaCaptacionesNaturalValidator extends AbstractValidator{
    
    public UsuariosAguaCaptacionesNaturalValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO tipoPersona = new ColumnTO("Tipo Persona",0);
        tipoPersona.addValidator(new Obligatorio());    
        tipoPersona.addValidator( new ListasValidator(1L, "Tipo Persona") );
        this.columnas.add( tipoPersona );        
        
        ColumnTO nombre = new ColumnTO("Nombre",1);
        nombre.addValidator( new Obligatorio() );               
        this.columnas.add( nombre );
        
        ColumnTO apellido = new ColumnTO("Apellido",2);
        apellido.addValidator( new Obligatorio() );        
        this.columnas.add( apellido );
        
        ColumnTO tipoDoc = new ColumnTO("Tipo Documento",3);
        tipoDoc.addValidator( new Obligatorio() );
        tipoDoc.addValidator( new ListasValidator(2L, "Tipo Id") );
        this.columnas.add( tipoDoc );
        
        ColumnTO numeroDoc = new ColumnTO("Numero Documento",4);
        numeroDoc.addValidator( new Obligatorio() );
        //numeroDoc.addValidator( new LongValidator() );             
        this.columnas.add( numeroDoc );
        
        ColumnTO depto = new ColumnTO("Departamento",5);        
        depto.addValidator( new Obligatorio() );        
        depto.addValidator( new DepartamentoValidator() );
        this.columnas.add( depto );
        
        ColumnTO municipio = new ColumnTO("Municipio",6);
        municipio.addValidator( new Obligatorio() );        
        municipio.addValidator( new MunicipioValidator(depto.getTitulo()) );        
        this.columnas.add( municipio );
        
        this.columnas.add( new ColumnTO("Direccion",7) );
        this.columnas.add( new ColumnTO("Email",8) );
        this.columnas.add( new ColumnTO("Telefono",9) );
        this.columnas.add( new ColumnTO("Fax",10) );                
        
        // Datos del Predio
        this.columnas.add( new ColumnTO("Nombre Predio",11) );
        
        ColumnTO tipoTenencia = new ColumnTO("Tipo Tenencia",12);
        tipoTenencia.addValidator( new Obligatorio() );
        tipoTenencia.addValidator( new ListasValidator(3L, "Tipo Tenencia") );
        this.columnas.add( tipoTenencia ); 
        
        ColumnTO deptoPredio = new ColumnTO("Departamento Predio",13);
        deptoPredio.addValidator( new Obligatorio() );        
        deptoPredio.addValidator( new DepartamentoValidator() );
        this.columnas.add( deptoPredio ); 
        
        ColumnTO munPredio = new ColumnTO("Municipio Predio",14);
        munPredio.addValidator( new Obligatorio() );
        munPredio.addValidator( new MunicipioValidator(deptoPredio.getTitulo()) );
        this.columnas.add( munPredio ); 
        
        ColumnTO tcpCaptacion = new ColumnTO("Tipo Centro Poblado",15);
        tcpCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_TIPO_CENTRO_POBLADO, 
                                                            "Tipo Centro Poblado") );
        this.columnas.add( tcpCaptacion ); 
        
        this.columnas.add( new ColumnTO("Nombre Centro Poblado",16) );
        
        ColumnTO cedulaCatastral = new ColumnTO("Cedula Catastral",17);
        cedulaCatastral.addValidator( new Obligatorio() );
        //cedulaCatastral.addValidator( new PredioCedulaValidator() );
        this.columnas.add( cedulaCatastral ); 
        
        ColumnTO matricula = new ColumnTO("Matricula Catastral",18);
        //matricula.addValidator( new Obligatorio() );
        this.columnas.add( matricula ); 
        
        ColumnTO area = new ColumnTO("Area",19);
        area.addValidator( new Obligatorio() );        
        area.addValidator( new DoubleValidator() );
        this.columnas.add( area ); 
        
        ColumnTO direccion = new ColumnTO("Direccion Predio",20);
        direccion.addValidator( new Obligatorio() );
        this.columnas.add( direccion );
        
        ColumnTO clasificacion = new ColumnTO("Clasificacion Suelo",21);
        clasificacion.addValidator( new Obligatorio() );
        clasificacion.addValidator( new ListasValidator(5L, "Clasificacion del Suelo") );
        this.columnas.add( clasificacion );
        
        ColumnTO sistemaReferencia = new ColumnTO("Sistema Referencia", 22);   
        sistemaReferencia.addValidator( new Obligatorio() );
        sistemaReferencia.addValidator( new ListasValidator(6L, "Sistema de Referencia") );
        this.columnas.add( sistemaReferencia );

        ColumnTO gradosLatitud = new ColumnTO("Grados Latitud", 23);
        gradosLatitud.addValidator( new Obligatorio() );
        gradosLatitud.addValidator( new LongValidator() );
        gradosLatitud.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitud );
        
        ColumnTO minutosLatitud = new ColumnTO("Minutos Latitud", 24);
        minutosLatitud.addValidator( new Obligatorio() );
        minutosLatitud.addValidator( new LongValidator());
        minutosLatitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitud );
        
        ColumnTO segundosLatitud = new ColumnTO("Segundos Latitud", 25);
        segundosLatitud.addValidator( new Obligatorio() );
        segundosLatitud.addValidator( new DoubleValidator() );
        segundosLatitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitud );
        
        ColumnTO gradosLongitud = new ColumnTO("Grados Longitud", 26);
        gradosLongitud.addValidator( new Obligatorio() );
        gradosLongitud.addValidator( new LongRangeValidator() );
        gradosLongitud.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitud );
        
        ColumnTO minutosLongitud = new ColumnTO("Minutos Longitud", 27);
        minutosLongitud.addValidator( new Obligatorio() );
        minutosLongitud.addValidator( new LongValidator());
        minutosLongitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitud );
        
        ColumnTO segundosLongitud = new ColumnTO("Segundos Longitud", 28);
        segundosLongitud.addValidator( new Obligatorio() );
        segundosLongitud.addValidator( new DoubleValidator() );
        segundosLongitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitud );

        ColumnTO altitud = new ColumnTO("Altitud", 29);
        altitud.addValidator( new Obligatorio() );
        altitud.addValidator( new DoubleValidator() );
        altitud.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitud );
        
        ColumnTO descripcion = new ColumnTO("Descripcion", 30);
        this.columnas.add( descripcion );
                
        
        // Datos de la Concesion
        ColumnTO numeroExpediente = new ColumnTO("Numero Expediente",31);
        numeroExpediente.addValidator( new Obligatorio() );
        this.columnas.add( numeroExpediente );
        
        ColumnTO numeroResolucion = new ColumnTO("Numero Resolucion", 32);
        numeroResolucion.addValidator( new Obligatorio() );
        //numeroResolucion.addValidator( new CaptacionResolucionValidator() );
        this.columnas.add( numeroResolucion );

        ColumnTO fechaExpResolucion = new ColumnTO("Fecha Expedicion Resolucion", 33);        
        this.columnas.add( fechaExpResolucion );
        
        ColumnTO fechaNotificacion = new ColumnTO("Fecha Notificacion", 34);
        fechaNotificacion.addValidator( new Obligatorio() );
        this.columnas.add( fechaNotificacion );
        
        ColumnTO caudal = new ColumnTO("Caudal", 35);
        caudal.addValidator( new Obligatorio() );
        caudal.addValidator( new DoubleValidator() );
        caudal.addValidator( new DoubleRangeValidator(0D, 9999999999D));
        this.columnas.add( caudal );
        
        ColumnTO resolucionPlanos = new ColumnTO("Resolucion Aprobacion Planos", 36);
        this.columnas.add( resolucionPlanos );
        
        ColumnTO fechaExpPlanos = new ColumnTO("Fecha Expedicion Aprobacion Planos", 37);
        this.columnas.add( fechaExpPlanos );
        
        ColumnTO fechaNotPlanos = new ColumnTO("Fecha Notificacion Aprobacion Planos", 38);
        this.columnas.add( fechaNotPlanos );
        
        ColumnTO resolucionObras = new ColumnTO("Resolucion Obras", 39);
        this.columnas.add( resolucionObras );
        
        ColumnTO fechaExpObras = new ColumnTO("Fecha Expedicion Obras",40);
        this.columnas.add( fechaExpObras );
        
        ColumnTO fechaNotObras = new ColumnTO("Fecha Notificacion Obras",41);
        this.columnas.add( fechaNotObras );
        
        ColumnTO vigenciaDesde = new ColumnTO("Vigencia Desde",42);
        this.columnas.add( vigenciaDesde );
        
        ColumnTO vigenciaHasta = new ColumnTO("Vigencia Hasta", 43);
        this.columnas.add( vigenciaHasta );
        
        
        ///EDUIN
                
        //Datos captacion        
        ColumnTO tipoFuente = new ColumnTO("Tipo Fuente Captacion", 44);
        tipoFuente.addValidator( new Obligatorio() );
        tipoFuente.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_FUENTE_CAPTA, 
                                                     "Tipo Fuente Captacion") );
        this.columnas.add( tipoFuente );
        
        ColumnTO areaCaptacion = new ColumnTO("Area Captacion", 45);
        areaCaptacion.addValidator( new Obligatorio() );        
        areaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( areaCaptacion );
        
        ColumnTO zonaCaptacion = new ColumnTO("Zona Captacion", 46);
        zonaCaptacion.addValidator( new Obligatorio() );
        zonaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( zonaCaptacion );

        ColumnTO subzonaCaptacion = new ColumnTO("Subzona Captacion", 47);
        subzonaCaptacion.addValidator( new Obligatorio() );
        subzonaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( subzonaCaptacion );
        
        
        //superficial
        
        ColumnTO tipo = new ColumnTO("Tipo Fuente", 48);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipo );
        
        ColumnTO nombreFuenteCapta = new ColumnTO("Nombre Fuente Captacion", 49);
        nombreFuenteCapta.addValidator(new Obligatorio());    
        nombreFuenteCapta.addValidator( new FuenteTipoNoExisteValidator(tipo.getTitulo(), 12L) );
        this.columnas.add( nombreFuenteCapta );
        
        ColumnTO nombreTramo = new ColumnTO("Nombre Tramo", 50);  
        nombreTramo.addValidator(new Obligatorio()); 
        nombreTramo.addValidator( new TramoCompositeValidator( tipo.getTitulo(), 
                                                                         12L, 
                                                                         nombreFuenteCapta.getTitulo()));
        this.columnas.add( nombreTramo );
        
        //subterraneas
        ColumnTO idPuntoSubterranea = new ColumnTO("Id Punto Subterranea", 51);        
        this.columnas.add( idPuntoSubterranea );
        
        ColumnTO idProvinciaHidro = new ColumnTO("Provincia Hidrologica", 52);        
        this.columnas.add( idProvinciaHidro );
        
        ColumnTO idUnidadHidro = new ColumnTO("Unidad Hidrologica", 53);        
        this.columnas.add( idUnidadHidro );
        
        
        
        //componentes
        ColumnTO aduccion = new ColumnTO("Aduccion", 54);
        this.columnas.add(aduccion);
        
        ColumnTO desarenador = new ColumnTO("Desarenador", 55);
        this.columnas.add(desarenador);
        
        ColumnTO ptap = new ColumnTO("PTAP", 56);
        this.columnas.add(ptap);
        
        ColumnTO red = new ColumnTO("Red Distribucion", 57);
        this.columnas.add(red);
        
        ColumnTO tanque = new ColumnTO("Tanque", 58);
        this.columnas.add(tanque);

        
        //detalle captacion
        ColumnTO tipoCaptacion = new ColumnTO("Tipo Captacion", 59);
        /*tipoCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_CAPTACION, 
                                                     "Tipo Captacion") );*/
        this.columnas.add( tipoCaptacion );
        
        ColumnTO ofertaTotal = new ColumnTO("Oferta Total", 60); 
        this.columnas.add( ofertaTotal );
        
        ColumnTO areaCapta = new ColumnTO("Area Captacion Lluvia", 61); 
        this.columnas.add( areaCapta );
        
        ColumnTO ofertaDisponible = new ColumnTO("Oferta Disponible", 62);        
        this.columnas.add( ofertaDisponible );
        
        ColumnTO macroMedicion = new ColumnTO("Macro Medicion", 63);        
        this.columnas.add( macroMedicion );
        
        ColumnTO estadoCaptacion = new ColumnTO("Estado Captacion", 64);
        /*estadoCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_ESTADO_CAPTACION, 
                                                     "Estado Captacion") );*/
        this.columnas.add( estadoCaptacion );
        
        ColumnTO caudalDisegno = new ColumnTO("Caudal Diseno", 65);        
        this.columnas.add( caudalDisegno );
        
        ColumnTO continuidad = new ColumnTO("Tiene Continuidad", 66);        
        this.columnas.add( continuidad );
        
        ColumnTO servidumbre = new ColumnTO("Tiene Servidumbre", 67);        
        this.columnas.add( servidumbre );
        
        ColumnTO sistemaReferenciaCaptacion = new ColumnTO("Sistema Referencia Captacion", 68);   
        sistemaReferenciaCaptacion.addValidator( new Obligatorio() );
        sistemaReferenciaCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA, 
                                                            "Sistema de Referencia Captacion") );
        this.columnas.add( sistemaReferenciaCaptacion );

        ColumnTO gradosLatitudCaptacion = new ColumnTO("Grados Latitud Captacion", 69);
        gradosLatitudCaptacion.addValidator( new Obligatorio() );
        gradosLatitudCaptacion.addValidator( new LongValidator() );
        gradosLatitudCaptacion.addValidator( new LongRangeValidator(Constantes.
                                                           LATITUD_GRADOS_MIN, 
                                                           Constantes.
                                                           LATITUD_GRADOS_MAX));
        this.columnas.add( gradosLatitudCaptacion );
        
        ColumnTO minutosLatitudCaptacion = new ColumnTO("Minutos Latitud Captacion", 70);
        minutosLatitudCaptacion.addValidator( new Obligatorio() );
        minutosLatitudCaptacion.addValidator( new LongValidator());
        minutosLatitudCaptacion.addValidator( new LongRangeValidator(Constantes.MINUTOS_MIN,
                                                            Constantes.MINUTOS_MAX) );
        this.columnas.add( minutosLatitudCaptacion );
        
        ColumnTO segundosLatitudCaptacion = new ColumnTO("Segundos Latitud Captacion", 71);
        segundosLatitudCaptacion.addValidator( new Obligatorio() );
        segundosLatitudCaptacion.addValidator( new DoubleValidator() );
        segundosLatitudCaptacion.addValidator( new DoubleRangeValidator(Constantes.
                                                               SEGUNDOS_MIN, 
                                                               Constantes.
                                                               SEGUNDOS_MAX) );
        this.columnas.add( segundosLatitudCaptacion );
        
        ColumnTO gradosLongitudCaptacion = new ColumnTO("Grados Longitud Captacion", 72);
        gradosLongitudCaptacion.addValidator( new Obligatorio() );
        gradosLongitudCaptacion.addValidator( new LongRangeValidator() );
        gradosLongitudCaptacion.addValidator( new LongRangeValidator(Constantes.
                                                           LONGITUD_GRADOS_MIN, 
                                                           Constantes.
                                                           LONGITUD_GRADOS_MAX));
        this.columnas.add( gradosLongitudCaptacion );
        
        ColumnTO minutosLongitudCaptacion = new ColumnTO("Minutos Longitud Captacion", 73);
        minutosLongitudCaptacion.addValidator( new Obligatorio() );
        minutosLongitudCaptacion.addValidator( new LongValidator());
        minutosLongitudCaptacion.addValidator( new LongRangeValidator(Constantes.MINUTOS_MIN,
                                                            Constantes.MINUTOS_MAX) );
        this.columnas.add( minutosLongitudCaptacion );
        
        ColumnTO segundosLongitudCaptacion = new ColumnTO("Segundos Longitud Captacion", 74);
        segundosLongitudCaptacion.addValidator( new Obligatorio() );
        segundosLongitudCaptacion.addValidator( new DoubleValidator() );
        segundosLongitudCaptacion.addValidator( new DoubleRangeValidator(Constantes.
                                                               SEGUNDOS_MIN, 
                                                               Constantes.
                                                               SEGUNDOS_MAX) );
        this.columnas.add( segundosLongitudCaptacion );

        ColumnTO altitudCaptacion = new ColumnTO("Altitud Captacion", 75);
        altitudCaptacion.addValidator( new Obligatorio() );
        altitudCaptacion.addValidator( new DoubleValidator() );
        altitudCaptacion.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitudCaptacion );
        
        ColumnTO descripcionCaptacion = new ColumnTO("Descripcion Acceso a Captacion", 76);
        this.columnas.add( descripcionCaptacion );
        
        //uso domestico
        ColumnTO caudalDomestico = new ColumnTO("Caudal Domestico", 77);
        this.columnas.add( caudalDomestico );
        
        ColumnTO personasPermanentes = new ColumnTO("Personas Permanentes", 78);
        this.columnas.add( personasPermanentes );
        
        ColumnTO personasTransitorias = new ColumnTO("Personas Transitorias", 79);
        this.columnas.add( personasTransitorias );
        
        ColumnTO aprovechamiento = new ColumnTO("Aprovechamiento", 80);
        this.columnas.add( aprovechamiento );
        
        //uso pecuario
        ColumnTO tipoAnimalPecuario = new ColumnTO("Tipo Animal Pecuario", 81);
        this.columnas.add( tipoAnimalPecuario );
        
        ColumnTO caudalPecuario = new ColumnTO("Caudal Pecuario", 82);
        this.columnas.add( caudalPecuario );
        
        ColumnTO numeroAnimalesPecuario = new ColumnTO("Numero Animales Pecuario", 83);
        this.columnas.add( numeroAnimalesPecuario );
        
        //uso acuicola
        ColumnTO tipoAnimalAcuicola = new ColumnTO("Tipo Animal Acuicola", 84);
        this.columnas.add( tipoAnimalAcuicola );
        
        ColumnTO caudalAcuicola = new ColumnTO("Caudal Acuicola", 85);
        this.columnas.add( caudalAcuicola );
        
        ColumnTO numeroAnimalesAcuicola = new ColumnTO("Numero Animales Acuicola", 86);
        this.columnas.add( numeroAnimalesAcuicola );
        
        //uso agricola
        ColumnTO tipoCultivo = new ColumnTO("Tipo Cultivo", 87);
        this.columnas.add( tipoCultivo );
        
        ColumnTO caudalAgricola = new ColumnTO("Caudal Agricola", 88);
        this.columnas.add( caudalAgricola );
         
        ColumnTO produccion = new ColumnTO("Produccion", 89);
        this.columnas.add( produccion );
        
        ColumnTO eficiencia = new ColumnTO("Eficiencia", 90);
        this.columnas.add( eficiencia );
        
        ColumnTO areaAgricola = new ColumnTO("Area Cultivada", 91);
        this.columnas.add( areaAgricola );
        
        //uso otro
        ColumnTO tipoUso = new ColumnTO("Otro Tipo Uso", 92);
        /*tipoUso.addValidator( new ListasValidator(ConstantesParametros.
                                                  CATEGORIA_OTROS_USOS_AGUA,
                                                  "Otro Tipo Uso") );*/
        this.columnas.add( tipoUso );
        
        ColumnTO descripcionOtro = new ColumnTO("Descripcion Otro Uso", 93);
        this.columnas.add( descripcionOtro );
        
        ColumnTO caudalOtro = new ColumnTO("Caudal Otro", 94);
        this.columnas.add( caudalOtro );
    }

}
