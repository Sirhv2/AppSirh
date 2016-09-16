package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.DoubleValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.FuenteTipoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasOpcionalValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongRangeValidator;
import co.gov.ideam.sirh.archivos.model.validator.LongValidator;
import co.gov.ideam.sirh.archivos.model.validator.MunicipioValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import co.gov.ideam.sirh.archivos.model.validator.TramoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class UsuariosAguaPermisosServiciosValidator  extends AbstractValidator{
    
    public UsuariosAguaPermisosServiciosValidator() {
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
        //numeroDoc.addValidator( new LongValidator() );             
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
        //numeroDocReresentante.addValidator( new LongValidator() );             
        this.columnas.add( numeroDocReresentante );
        
        ColumnTO deptoReresentante = new ColumnTO("Departamento Representante",13);        
        deptoReresentante.addValidator( new Obligatorio() );        
        deptoReresentante.addValidator( new DepartamentoValidator() );
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
        //cedulaCatastral.addValidator( new Obligatorio() );
        //cedulaCatastral.addValidator( new PredioCedulaValidator() );
        this.columnas.add( cedulaCatastral ); 
        
        ColumnTO matricula = new ColumnTO("Matricula Catastral",24);
        //matricula.addValidator( new Obligatorio() );
        this.columnas.add( matricula ); 

        ColumnTO area = new ColumnTO("Area",25);
        //area.addValidator( new Obligatorio() );        
        //area.addValidator( new DoubleValidator() );
        this.columnas.add( area ); 
        
        ColumnTO direccion = new ColumnTO("Direccion Predio",26);
        //direccion.addValidator( new Obligatorio() );
        this.columnas.add( direccion );
        
        ColumnTO clasificacion = new ColumnTO("Clasificacion Suelo",27);
        //clasificacion.addValidator( new Obligatorio() );
        clasificacion.addValidator( new ListasOpcionalValidator(5L, "Clasificacion del Suelo") );
        this.columnas.add( clasificacion );
                        
        // Datos del Permiso de Vertimiento
        ColumnTO actoAdmin = new ColumnTO("Acto Administrativo",28);
        actoAdmin.addValidator( new Obligatorio() );
        this.columnas.add( actoAdmin );
        
        ColumnTO fechaExpResolucion = new ColumnTO("Fecha Expedicion", 29);        
        this.columnas.add( fechaExpResolucion );
        
        ColumnTO numeroExpediente = new ColumnTO("Numero Expediente",30);
        numeroExpediente.addValidator( new Obligatorio() );
        this.columnas.add( numeroExpediente );
        
        ColumnTO caudal = new ColumnTO("Caudal", 31);
        caudal.addValidator( new Obligatorio() );
        caudal.addValidator( new DoubleValidator() );
        caudal.addValidator( new DoubleRangeValidator(0D, 9999999999D));
        this.columnas.add( caudal );

        ColumnTO evaluacionAmbiental = new ColumnTO("Evaluacion Ambiental", 32);
        this.columnas.add( evaluacionAmbiental );
        
        // Plan de cumplimiento
        ColumnTO actoPlanCum = new ColumnTO("Acto Administrativo PC", 33);
        this.columnas.add( actoPlanCum );                
        this.columnas.add( new ColumnTO("Fecha Expedicion PC", 34) );                
        this.columnas.add( new ColumnTO("Acto Administrativo Aprobacion PC", 35) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion PC", 36) );        
        this.columnas.add( new ColumnTO("Fecha Notificacion Aprobacion PC", 37) );
        this.columnas.add( new ColumnTO("Fecha Inicial Plan Cumplimiento", 38) );
        this.columnas.add( new ColumnTO("Fecha Final Plan Cumplimiento", 39) );
        
        // Permiso de Vertimiento
        this.columnas.add( new ColumnTO("Resolucion Permiso Vertimiento", 40) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Permiso Vertimiento", 41) );                
        this.columnas.add( new ColumnTO("Fecha Inicial Permiso Vertimiento", 42) );
        this.columnas.add( new ColumnTO("Fecha Final Permiso Vertimiento", 43) );
                
        this.columnas.add( new ColumnTO("Resolucion Aprobacion Planos", 44) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion Planos", 45) );                
        this.columnas.add( new ColumnTO("Resolucion Aprobacion Obras", 46) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion Obras", 47) );
        this.columnas.add( new ColumnTO("Fecha Notificacion Aprobacion Obras", 48) );
        
        //PUNTO DE VERTIMIENTO
        
        ColumnTO tipoVert = new ColumnTO("Tipo Vertimiento", 49);
        tipoVert.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_VERTIMIENTO, 
                                                     "Tipo Vertimiento") );
        this.columnas.add( tipoVert );
        
        ColumnTO deptoVert = new ColumnTO("Departamento Vertimiento",50);
        deptoVert.addValidator( new Obligatorio() );        
        deptoVert.addValidator( new DepartamentoValidator() );
        this.columnas.add( deptoVert ); 
        
        ColumnTO munVert = new ColumnTO("Municipio Vertimiento",51);
        munVert.addValidator( new Obligatorio() );
        munVert.addValidator( new MunicipioValidator(deptoVert.getTitulo()) );
        this.columnas.add( munVert ); 
        
        this.columnas.add( new ColumnTO("Tipo centro Vertimiento", 52) );
        this.columnas.add( new ColumnTO("Nombre centro Vertimiento", 53) );
        
        ColumnTO areaVert = new ColumnTO("Area", 54);
        //areaVert.addValidator( new Obligatorio() );        
        //areaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( areaVert );
        
        ColumnTO zonaVert = new ColumnTO("Zona", 55);
        //zonaVert.addValidator( new Obligatorio() );
        //zonaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( zonaVert );

        ColumnTO subzonaVert = new ColumnTO("Subzona", 56);
        //subzonaVert.addValidator( new Obligatorio() );
        //subzonaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( subzonaVert );
        
        ColumnTO tipo = new ColumnTO("Tipo Fuente", 57);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipo );
        
        ColumnTO nombreFuenteCapta = new ColumnTO("Fuente", 58);
        nombreFuenteCapta.addValidator(new Obligatorio());    
        nombreFuenteCapta.addValidator( new FuenteTipoNoExisteValidator(tipo.getTitulo(), 12L));
        this.columnas.add( nombreFuenteCapta );
        
        ColumnTO nombreTramo = new ColumnTO("Tramo", 59);  
        //nombreTramo.addValidator(new Obligatorio()); 
        //nombreTramo.addValidator( new TramoNoExisteValidator( nombreFuenteCapta.getTitulo() ) );
        this.columnas.add( nombreTramo );
        
        ColumnTO tipoFlujo = new ColumnTO("Tipo Flujo", 60);
        /*tipoFlujo.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_FLUJO_VERTIMIENTO, 
                                                     "Tipo Flujo") );*/
        this.columnas.add( tipoFlujo );
        
        this.columnas.add( new ColumnTO("Tiempo Descarga", 61) );
        this.columnas.add( new ColumnTO("Frecuencia", 62) );
        this.columnas.add( new ColumnTO("Caudal Descarga", 63) );

        ColumnTO preTratamiento = new ColumnTO("Pretratamiento", 64);
        preTratamiento.addValidator( new ListasValidator(ConstantesParametros.CATEGORIA_PRETRATAMIENTO, "Pretratamiento") );
        this.columnas.add( preTratamiento );
        
        ColumnTO primario = new ColumnTO("Primario", 65);
        primario.addValidator( new ListasValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_PRIMARIO, "Primario") );
        this.columnas.add( primario );
        
        ColumnTO secundario = new ColumnTO("Secundario", 66);
        secundario.addValidator( new ListasValidator(ConstantesParametros. CATEGORIA_TRATAMIENTO_SECUNDARIO,  "Secundario") );
        this.columnas.add( secundario );
        
        ColumnTO terciario = new ColumnTO("Terciario", 67);
        terciario.addValidator( new ListasValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_TERCIARIO, "Terciario") );
        this.columnas.add( terciario );
        
        ColumnTO otro = new ColumnTO("Otros", 68);
        otro.addValidator( new ListasValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_OTRO, "Otros") );
        this.columnas.add( otro );        
                
        ColumnTO sistemaReferencia = new ColumnTO("Sistema Referencia", 69);   
        sistemaReferencia.addValidator( new Obligatorio() );
        sistemaReferencia.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA, 
                                                            "Sistema de Referencia") );
        this.columnas.add( sistemaReferencia );

        ColumnTO gradosLatitud = new ColumnTO("Grados Latitud", 70);
        gradosLatitud.addValidator( new Obligatorio() );
        gradosLatitud.addValidator( new LongValidator() );
        gradosLatitud.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitud );
        
        ColumnTO minutosLatitud = new ColumnTO("Minutos Latitud", 71);
        minutosLatitud.addValidator( new Obligatorio() );
        minutosLatitud.addValidator( new LongValidator());
        minutosLatitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitud );
        
        ColumnTO segundosLatitud = new ColumnTO("Segundos Latitud", 72);
        segundosLatitud.addValidator( new Obligatorio() );
        segundosLatitud.addValidator( new DoubleValidator() );
        segundosLatitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitud );
        
        ColumnTO gradosLongitud = new ColumnTO("Grados Longitud", 73);
        gradosLongitud.addValidator( new Obligatorio() );
        gradosLongitud.addValidator( new LongRangeValidator() );
        gradosLongitud.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitud );
        
        ColumnTO minutosLongitud = new ColumnTO("Minutos Longitud", 74);
        minutosLongitud.addValidator( new Obligatorio() );
        minutosLongitud.addValidator( new LongValidator());
        minutosLongitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitud );
        
        ColumnTO segundosLongitud = new ColumnTO("Segundos Longitud", 75);
        segundosLongitud.addValidator( new Obligatorio() );
        segundosLongitud.addValidator( new DoubleValidator() );
        segundosLongitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitud );

        ColumnTO altitud = new ColumnTO("Altitud", 76);
        altitud.addValidator( new Obligatorio() );
        altitud.addValidator( new DoubleValidator() );
        altitud.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitud );
        
        ColumnTO descripcion = new ColumnTO("Descripcion", 77);
        this.columnas.add( descripcion );
        
        
        ///
        ColumnTO resolucionPsmv = new ColumnTO("Acto Administrativo PSMV", 78);
        this.columnas.add( resolucionPsmv );
        
        ColumnTO fechaExpPsmv = new ColumnTO("Fecha Expedicion PSMV", 79);
        this.columnas.add( fechaExpPsmv );
        
        ColumnTO fechaInicioPsmv = new ColumnTO("Fecha Inicio PSMV", 80);
        this.columnas.add( fechaInicioPsmv );
        
        ColumnTO fechaFinPsmv = new ColumnTO("Fecha Inicio PSMV", 81);
        this.columnas.add( fechaFinPsmv );
        
        ColumnTO planosPsmv = new ColumnTO("Resolucion Planos PSMV",82);
        this.columnas.add( planosPsmv );
        
        ColumnTO fechaExpPlanosPsmv = new ColumnTO("Fecha Expedicion Planos PSMV",83);
        this.columnas.add( fechaExpPlanosPsmv );
        
        ColumnTO obrasPsmv = new ColumnTO("Resolucion Obras PSMV",84);
        this.columnas.add( obrasPsmv );
        
        ColumnTO fechaExpObrasPsmv = new ColumnTO("Fecha Expedicion Obras PSMV",85);
        this.columnas.add( fechaExpObrasPsmv );
        
        ColumnTO fechaNotObrasPsmv = new ColumnTO("Fecha Notificacion Obras PSMV",86);
        this.columnas.add( fechaNotObrasPsmv );
        
        ColumnTO observaciones = new ColumnTO("Observaciones",87);
        this.columnas.add( observaciones );
        
        
        
        
        
    }
}
