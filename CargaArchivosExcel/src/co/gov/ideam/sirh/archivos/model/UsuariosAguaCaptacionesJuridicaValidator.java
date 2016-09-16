package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.CaptacionResolucionValidator;
import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DivipolaValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.archivos.model.validator.TramoCompositeValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class UsuariosAguaCaptacionesJuridicaValidator extends AbstractValidator{

    public UsuariosAguaCaptacionesJuridicaValidator() {
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO tipoPersona = new ColumnTO("Tipo Persona",0);
        tipoPersona.addValidator(new Obligatorio());
        tipoPersona.addValidator( new ListasValidator(1L, "Tipo Persona") );
        this.columnas.add( tipoPersona );

        ColumnTO nombre = new ColumnTO("Razon Social",1);
        nombre.addValidator( new Obligatorio() );
        this.columnas.add( nombre );

        ColumnTO tipoDoc = new ColumnTO("Tipo Documento",2);
        tipoDoc.addValidator( new Obligatorio() );
        tipoDoc.addValidator( new ListasValidator(2L, "Tipo Id") );
        this.columnas.add( tipoDoc );

        ColumnTO numeroDoc = new ColumnTO("Numero Documento",3);
        numeroDoc.addValidator( new Obligatorio() );
      //  numeroDoc.addValidator( new LongValidator() );
        this.columnas.add( numeroDoc );

        ColumnTO actividadEconomica = new ColumnTO("Actividad Economica",4);
        this.columnas.add( actividadEconomica );

        this.columnas.add( new ColumnTO("Direccion",5) );
        this.columnas.add( new ColumnTO("Email",6) );
        this.columnas.add( new ColumnTO("Telefono",7) );
        this.columnas.add( new ColumnTO("Fax",8) );

        // Datos del Reresentante Legal
        ColumnTO nombreReresentante = new ColumnTO("Nombre Representante", 9);
        this.columnas.add( nombreReresentante );

        ColumnTO aellidoReresentante = new ColumnTO("Apellido Representante", 10);
        this.columnas.add( aellidoReresentante );

        ColumnTO tipoDocReresentante = new ColumnTO("Tipo Documento Representante",11);
        tipoDocReresentante.addValidator( new Obligatorio() );
        tipoDocReresentante.addValidator( new ListasValidator(2L, "Tipo Id") );
        this.columnas.add( tipoDocReresentante );

        ColumnTO numeroDocReresentante = new ColumnTO("Numero Documento Representante",12);
        numeroDocReresentante.addValidator( new Obligatorio() );
       // numeroDocReresentante.addValidator( new LongValidator() );
        this.columnas.add( numeroDocReresentante );

        ColumnTO deptoReresentante = new ColumnTO("Departamento Representante",13);
        deptoReresentante.addValidator( new Obligatorio() );
        deptoReresentante.addValidator( new DepartamentoValidator());
        this.columnas.add( deptoReresentante );

        ColumnTO municipioReresentante = new ColumnTO("Municipio Representante",14);
        municipioReresentante.addValidator( new Obligatorio() );
        municipioReresentante.addValidator( new MunicipioValidator(deptoReresentante.getTitulo()) );
        this.columnas.add( municipioReresentante );

        ColumnTO dirReresentante =  new ColumnTO("Direccion Representante",15) ;
        dirReresentante.addValidator( new Obligatorio() );
        this.columnas.add(dirReresentante);
        
        this.columnas.add( new ColumnTO("Telefono Representante",16) );

        // Datos del centro poblado
        this.columnas.add( new ColumnTO("Nombre predio",17) );

        ColumnTO tipoTenencia = new ColumnTO("Tipo Tenencia",18);
        tipoTenencia.addValidator( new Obligatorio() );
        tipoTenencia.addValidator( new ListasValidator(3L, "Tipo Tenencia") );
        this.columnas.add( tipoTenencia );

        ColumnTO deptoPredio = new ColumnTO("Departamento Predio",19);
        deptoPredio.addValidator( new Obligatorio() );
        deptoPredio.addValidator( new DepartamentoValidator() );
        this.columnas.add( deptoPredio );

        ColumnTO munPredio = new ColumnTO("Municipio Predio",20);
        munPredio.addValidator( new Obligatorio() );
        munPredio.addValidator( new MunicipioValidator(deptoPredio.getTitulo()) );
        this.columnas.add( munPredio );

        this.columnas.add( new ColumnTO("Tipo Centro Poblado",21) );
        this.columnas.add( new ColumnTO("Nombre Centro Poblado",22) );

        ColumnTO cedulaCatastral = new ColumnTO("Cedula Catastral",23);
        cedulaCatastral.addValidator( new Obligatorio() );
        //cedulaCatastral.addValidator( new PredioCedulaValidator() );
        this.columnas.add( cedulaCatastral );

        ColumnTO matricula = new ColumnTO("Matricula Catastral",24);
       // matricula.addValidator( new Obligatorio() );
        this.columnas.add( matricula );

        ColumnTO area = new ColumnTO("Area",25);
        area.addValidator( new Obligatorio() );
        area.addValidator( new DoubleValidator() );
        this.columnas.add( area );

        ColumnTO direccion = new ColumnTO("Direccion Predio",26);
        direccion.addValidator( new Obligatorio() );
        this.columnas.add( direccion );

        ColumnTO caudal = new ColumnTO("Caudal", 27);
        caudal.addValidator( new Obligatorio() );
        caudal.addValidator( new DoubleValidator() );
        caudal.addValidator( new DoubleRangeValidator(0D, 9999999999D));
        this.columnas.add( caudal );

        ColumnTO clasificacion = new ColumnTO("Clasificacion Suelo",28);
        clasificacion.addValidator( new Obligatorio() );
        clasificacion.addValidator( new ListasValidator(5L, "Clasificacion del Suelo") );
        this.columnas.add( clasificacion );

        ColumnTO sistemaReferencia = new ColumnTO("Sistema Referencia", 29);
        sistemaReferencia.addValidator( new Obligatorio() );
        sistemaReferencia.addValidator( new ListasValidator(6L, "Sistema de Referencia") );
        this.columnas.add( sistemaReferencia );

        ColumnTO gradosLatitud = new ColumnTO("Grados Latitud", 30);
        gradosLatitud.addValidator( new Obligatorio() );
        gradosLatitud.addValidator( new LongValidator() );
        gradosLatitud.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitud );

        ColumnTO minutosLatitud = new ColumnTO("Minutos Latitud",31);
        minutosLatitud.addValidator( new Obligatorio() );
        minutosLatitud.addValidator( new LongValidator());
        minutosLatitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitud );

        ColumnTO segundosLatitud = new ColumnTO("Segundos Latitud", 32);
        segundosLatitud.addValidator( new Obligatorio() );
        segundosLatitud.addValidator( new DoubleValidator() );
        segundosLatitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitud );

        ColumnTO gradosLongitud = new ColumnTO("Grados Longitud", 33);
        gradosLongitud.addValidator( new Obligatorio() );
        gradosLongitud.addValidator( new LongRangeValidator() );
        gradosLongitud.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitud );

        ColumnTO minutosLongitud = new ColumnTO("Minutos Longitud", 34);
        minutosLongitud.addValidator( new Obligatorio() );
        minutosLongitud.addValidator( new LongValidator());
        minutosLongitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitud );

        ColumnTO segundosLongitud = new ColumnTO("Segundos Longitud", 35);
        segundosLongitud.addValidator( new Obligatorio() );
        segundosLongitud.addValidator( new DoubleValidator() );
        segundosLongitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitud );

        ColumnTO altitud = new ColumnTO("Altitud", 36);
        altitud.addValidator( new Obligatorio() );
        altitud.addValidator( new DoubleValidator() );
        altitud.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitud );

        ColumnTO descripcion = new ColumnTO("Descripcion", 37);
        this.columnas.add( descripcion );

        // Datos de la Concesion
        ColumnTO numeroExpediente = new ColumnTO("Numero Expediente",38);
        numeroExpediente.addValidator( new Obligatorio() );
        this.columnas.add( numeroExpediente );

        ColumnTO numeroResolucion = new ColumnTO("Numero Resolucion", 39);
        numeroResolucion.addValidator( new Obligatorio() );
        //numeroResolucion.addValidator( new CaptacionResolucionValidator() );
        this.columnas.add( numeroResolucion );

        ColumnTO fechaExpResolucion = new ColumnTO("Fecha Expedicion Resolucion", 40);
        this.columnas.add( fechaExpResolucion );

        ColumnTO fechaNotificacion = new ColumnTO("Fecha Notificacion", 41);
        fechaNotificacion.addValidator( new Obligatorio() );
        this.columnas.add( fechaNotificacion );

        ColumnTO resolucionPlanos = new ColumnTO("Resolucion Aprobacion Planos", 42);
        this.columnas.add( resolucionPlanos );

        ColumnTO fechaExpPlanos = new ColumnTO("Fecha Expedicion Aprobacion Planos", 43);
        this.columnas.add( fechaExpPlanos );

        ColumnTO fechaNotPlanos = new ColumnTO("Fecha Notificacion Aprobacion Planos", 44);
        this.columnas.add( fechaNotPlanos );

        ColumnTO resolucionObras = new ColumnTO("Resolucion Obras", 45);
        this.columnas.add( resolucionObras );

        ColumnTO fechaExpObras = new ColumnTO("Fecha Expedicion Obras",46);
        this.columnas.add( fechaExpObras );

        ColumnTO fechaNotObras = new ColumnTO("Fecha Notificacion Obras",47);
        this.columnas.add( fechaNotObras );

        ColumnTO vigenciaDesde = new ColumnTO("Vigencia Desde",48);
        this.columnas.add( vigenciaDesde );

        ColumnTO vigenciaHasta = new ColumnTO("Vigencia Hasta", 49);
        this.columnas.add( vigenciaHasta );

        ///EDUIN

        //Datos captacion
        ColumnTO tipoFuente = new ColumnTO("Tipo Fuente Captacion", 50);
        tipoFuente.addValidator( new Obligatorio() );
        tipoFuente.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_FUENTE_CAPTA,
                                                     "Tipo Fuente Captacion") );
        this.columnas.add( tipoFuente );

        ColumnTO deptoCaptacion = new ColumnTO("Departamento Captacion",51);
        deptoCaptacion.addValidator( new Obligatorio() );
        deptoCaptacion.addValidator( new DepartamentoValidator() );
        this.columnas.add( deptoCaptacion );

        ColumnTO munCaptacion = new ColumnTO("Municipio Captacion",52);
        munCaptacion.addValidator( new Obligatorio() );
        munCaptacion.addValidator( new MunicipioValidator(deptoCaptacion.getTitulo()) );
        this.columnas.add( munCaptacion );

        ColumnTO tcpCaptacion = new ColumnTO("Tipo Centro Poblado Captacion",53);
        tcpCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_TIPO_CENTRO_POBLADO,
                                                            "Tipo Centro Poblado Captacion") );
        this.columnas.add( tcpCaptacion );


        this.columnas.add( new ColumnTO("Nombre Centro Poblado Captacion",54) );

        //que es id punto aguas subterraneas... se está repitiendo

        ColumnTO areaCaptacion = new ColumnTO("Area Captacion", 55);
        areaCaptacion.addValidator( new Obligatorio() );
        areaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( areaCaptacion );

        ColumnTO zonaCaptacion = new ColumnTO("Zona Captacion", 56);
        zonaCaptacion.addValidator( new Obligatorio() );
        zonaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( zonaCaptacion );

        ColumnTO subzonaCaptacion = new ColumnTO("Subzona Captacion", 57);
        subzonaCaptacion.addValidator( new Obligatorio() );
        subzonaCaptacion.addValidator( new ZonificacionValidator() );
        this.columnas.add( subzonaCaptacion );

        //superficial
        ColumnTO tipo = new ColumnTO("Tipo Fuente", 58);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipo );

        ColumnTO nombreFuenteCapta = new ColumnTO("Nombre Fuente Captacion", 59);
        nombreFuenteCapta.addValidator(new Obligatorio());
        nombreFuenteCapta.addValidator( new FuenteTipoNoExisteValidator(tipo.getTitulo(), 12L));
        this.columnas.add( nombreFuenteCapta );

        ColumnTO nombreTramo = new ColumnTO("Nombre Tramo", 60);
        nombreTramo.addValidator(new Obligatorio());
        nombreTramo.addValidator( new TramoCompositeValidator( tipo.getTitulo(),
                                                                         12L,
                                                                         nombreFuenteCapta.getTitulo()));
        this.columnas.add( nombreTramo );

        //subterraneas
        ColumnTO idPuntoSubterranea = new ColumnTO("Id Punto Subterranea", 61);
        this.columnas.add( idPuntoSubterranea );

        ColumnTO idProvinciaHidro = new ColumnTO("Provincia Hidrologica", 62);
        this.columnas.add( idProvinciaHidro );

        ColumnTO idUnidadHidro = new ColumnTO("Unidad Hidrologica", 63);
        this.columnas.add( idUnidadHidro );

        //componentes
        ColumnTO aduccion = new ColumnTO("Aduccion", 64);
        this.columnas.add(aduccion);

        ColumnTO desarenador = new ColumnTO("Desarenador", 65);
        this.columnas.add(desarenador);

        ColumnTO ptap = new ColumnTO("PTAP", 66);
        this.columnas.add(ptap);

        ColumnTO red = new ColumnTO("Red Distribucion", 67);
        this.columnas.add(red);

        ColumnTO tanque = new ColumnTO("Tanque", 68);
        this.columnas.add(tanque);

        //detalle captacion
        ColumnTO tipoCaptacion = new ColumnTO("Tipo Captacion", 69);
        /*tipoCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_CAPTACION,
                                                     "Tipo Captacion") );*/
        this.columnas.add( tipoCaptacion );

        ColumnTO ofertaTotal = new ColumnTO("Oferta Total", 70);
        this.columnas.add( ofertaTotal );

        ColumnTO areaCapta = new ColumnTO("Area Captacion Lluvia", 71);
        this.columnas.add( areaCapta );

        ColumnTO ofertaDisponible = new ColumnTO("Oferta Disponible", 72);
        this.columnas.add( ofertaDisponible );

        ColumnTO macroMedicion = new ColumnTO("Macro Medicion", 73);
        this.columnas.add( macroMedicion );

        ColumnTO estadoCaptacion = new ColumnTO("Estado Captacion", 74);
        /*estadoCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_ESTADO_CAPTACION,
                                                     "Estado Captacion") );*/
        this.columnas.add( estadoCaptacion );

        ColumnTO caudalDisegno = new ColumnTO("Caudal Diseno", 75);
        this.columnas.add( caudalDisegno );

        ColumnTO continuidad = new ColumnTO("Tiene Continuidad", 76);
        this.columnas.add( continuidad );

        ColumnTO servidumbre = new ColumnTO("Tiene Servidumbre", 77);
        this.columnas.add( servidumbre );

        ColumnTO sistemaReferenciaCaptacion = new ColumnTO("Sistema Referencia Captacion", 78);
        sistemaReferenciaCaptacion.addValidator( new Obligatorio() );
        sistemaReferenciaCaptacion.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA,
                                                            "Sistema de Referencia Captacion") );
        this.columnas.add( sistemaReferenciaCaptacion );

        ColumnTO gradosLatitudCaptacion = new ColumnTO("Grados Latitud Captacion", 79);
        gradosLatitudCaptacion.addValidator( new Obligatorio() );
        gradosLatitudCaptacion.addValidator( new LongValidator() );
        gradosLatitudCaptacion.addValidator( new LongRangeValidator(Constantes.
                                                           LATITUD_GRADOS_MIN,
                                                           Constantes.
                                                           LATITUD_GRADOS_MAX));
        this.columnas.add( gradosLatitudCaptacion );
        System.out.println("gradosLatitud de la captacion:---------------------------"+gradosLatitudCaptacion);

        ColumnTO minutosLatitudCaptacion = new ColumnTO("Minutos Latitud Captacion", 80);
        minutosLatitudCaptacion.addValidator( new Obligatorio() );
        minutosLatitudCaptacion.addValidator( new LongValidator());
        minutosLatitudCaptacion.addValidator( new LongRangeValidator(Constantes.MINUTOS_MIN,
                                                            Constantes.MINUTOS_MAX) );
        this.columnas.add( minutosLatitudCaptacion );

        ColumnTO segundosLatitudCaptacion = new ColumnTO("Segundos Latitud Captacion", 81);
        segundosLatitudCaptacion.addValidator( new Obligatorio() );
        segundosLatitudCaptacion.addValidator( new DoubleValidator() );
        segundosLatitudCaptacion.addValidator( new DoubleRangeValidator(Constantes.
                                                               SEGUNDOS_MIN,
                                                               Constantes.
                                                               SEGUNDOS_MAX) );
        this.columnas.add( segundosLatitudCaptacion );

        ColumnTO gradosLongitudCaptacion = new ColumnTO("Grados Longitud Captacion", 82);
        gradosLongitudCaptacion.addValidator( new Obligatorio() );
        gradosLongitudCaptacion.addValidator( new LongRangeValidator() );
        gradosLongitudCaptacion.addValidator( new LongRangeValidator(Constantes.
                                                           LONGITUD_GRADOS_MIN,
                                                           Constantes.
                                                           LONGITUD_GRADOS_MAX));
        this.columnas.add( gradosLongitudCaptacion );

        ColumnTO minutosLongitudCaptacion = new ColumnTO("Minutos Longitud Captacion", 83);
        minutosLongitudCaptacion.addValidator( new Obligatorio() );
        minutosLongitudCaptacion.addValidator( new LongValidator());
        minutosLongitudCaptacion.addValidator( new LongRangeValidator(Constantes.MINUTOS_MIN,
                                                            Constantes.MINUTOS_MAX) );
        this.columnas.add( minutosLongitudCaptacion );

        ColumnTO segundosLongitudCaptacion = new ColumnTO("Segundos Longitud Captacion", 84);
        segundosLongitudCaptacion.addValidator( new Obligatorio() );
        segundosLongitudCaptacion.addValidator( new DoubleValidator() );
        segundosLongitudCaptacion.addValidator( new DoubleRangeValidator(Constantes.
                                                               SEGUNDOS_MIN,
                                                               Constantes.
                                                               SEGUNDOS_MAX) );
        this.columnas.add( segundosLongitudCaptacion );

        ColumnTO altitudCaptacion = new ColumnTO("Altitud Captacion", 85);
        altitudCaptacion.addValidator( new Obligatorio() );
        altitudCaptacion.addValidator( new DoubleValidator() );
        altitudCaptacion.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitudCaptacion );

        ColumnTO descripcionCaptacion = new ColumnTO("Descripcion Acceso a Captacion", 86);
        this.columnas.add( descripcionCaptacion );

        //uso domestico
        ColumnTO caudalDomestico = new ColumnTO("Caudal Domestico", 87);
        this.columnas.add( caudalDomestico );

        ColumnTO personasPermanentes = new ColumnTO("Personas Permanentes", 88);
        this.columnas.add( personasPermanentes );

        ColumnTO personasTransitorias = new ColumnTO("Personas Transitorias", 89);
        this.columnas.add( personasTransitorias );

        ColumnTO aprovechamiento = new ColumnTO("Aprovechamiento", 90);
        this.columnas.add( aprovechamiento );

        //uso pecuario
        ColumnTO tipoAnimalPecuario = new ColumnTO("Tipo Animal Pecuario", 91);
        this.columnas.add( tipoAnimalPecuario );

        ColumnTO caudalPecuario = new ColumnTO("Caudal Pecuario", 92);
        this.columnas.add( caudalPecuario );

        ColumnTO numeroAnimalesPecuario = new ColumnTO("Numero Animales Pecuario", 93);
        this.columnas.add( numeroAnimalesPecuario );

        //uso acuicola
        ColumnTO tipoAnimalAcuicola = new ColumnTO("Tipo Animal Acuicola", 94);
        this.columnas.add( tipoAnimalAcuicola );

        ColumnTO caudalAcuicola = new ColumnTO("Caudal Acuicola", 95);
        this.columnas.add( caudalAcuicola );

        ColumnTO numeroAnimalesAcuicola = new ColumnTO("Numero Animales Acuicola", 96);
        this.columnas.add( numeroAnimalesAcuicola );

        //uso agricola
        ColumnTO tipoCultivo = new ColumnTO("Tipo Cultivo", 97);
        this.columnas.add( tipoCultivo );

        ColumnTO caudalAgricola = new ColumnTO("Caudal Agricola", 98);
        this.columnas.add( caudalAgricola );

        ColumnTO produccion = new ColumnTO("Produccion", 99);
        this.columnas.add( produccion );

        ColumnTO eficiencia = new ColumnTO("Eficiencia", 100);
        this.columnas.add( eficiencia );

        ColumnTO areaAgricola = new ColumnTO("Area Cultivada", 101);
        this.columnas.add( areaAgricola );

        //uso otro
        ColumnTO tipoUso = new ColumnTO("Otro Tipo Uso", 102);
        /*tipoUso.addValidator( new ListasValidator(ConstantesParametros.
                                                  CATEGORIA_OTROS_USOS_AGUA,
                                                  "Otro Tipo Uso") );*/
        this.columnas.add( tipoUso );

        ColumnTO descripcionOtro = new ColumnTO("Descripcion Otro Uso", 103);
        this.columnas.add( descripcionOtro );

        ColumnTO caudalOtro = new ColumnTO("Caudal Otro", 104);
        this.columnas.add( caudalOtro );

    }
}
