package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.CaptacionResolucionValidator;
import co.gov.ideam.sirh.archivos.model.validator.DepartamentoValidator;
import co.gov.ideam.sirh.archivos.model.validator.DivipolaValidator;
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
import co.gov.ideam.sirh.archivos.model.validator.PredioCedulaValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoCompositeValidator;
import co.gov.ideam.sirh.archivos.model.validator.TramoNoExisteValidator;
import co.gov.ideam.sirh.archivos.model.validator.ZonificacionValidator;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class UsuariosAguaPermisosNaturalValidator extends AbstractValidator{
    
    public UsuariosAguaPermisosNaturalValidator() {
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
        depto.addValidator( new DepartamentoValidator());
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
        munPredio.addValidator( new MunicipioValidator( deptoPredio.getTitulo() ) );
        this.columnas.add( munPredio ); 

        ColumnTO tcpVertimiento = new ColumnTO("Tipo Centro Poblado",15);
        tcpVertimiento.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_TIPO_CENTRO_POBLADO, 
                                                            "Tipo Centro Poblado") );
        this.columnas.add( tcpVertimiento ); 
        
        this.columnas.add( new ColumnTO("Nombre Centro Poblado",16) );
        
        ColumnTO cedulaCatastral = new ColumnTO("Cedula Catastral",17);
        cedulaCatastral.addValidator( new Obligatorio() );
        //cedulaCatastral.addValidator( new PredioCedulaValidator() );
        this.columnas.add( cedulaCatastral ); 
        
        ColumnTO matricula = new ColumnTO("Matricula Catastral",18);
       // matricula.addValidator( new Obligatorio() );
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
        
        // Datos del Permiso de Vertimiento
        ColumnTO actoAdmin = new ColumnTO("Acto Administrativo",22);
        actoAdmin.addValidator( new Obligatorio() );
        this.columnas.add( actoAdmin );
        
        ColumnTO fechaExpResolucion = new ColumnTO("Fecha Expedicion", 23);        
        this.columnas.add( fechaExpResolucion );
        
        ColumnTO numeroExpediente = new ColumnTO("Numero Expediente",24);
        numeroExpediente.addValidator( new Obligatorio() );
        this.columnas.add( numeroExpediente );
        
        ColumnTO caudal = new ColumnTO("Caudal", 25);
        caudal.addValidator( new Obligatorio() );
        caudal.addValidator( new DoubleValidator() );
        caudal.addValidator( new DoubleRangeValidator(0D, 9999999999D));
        this.columnas.add( caudal );

        ColumnTO evaluacionAmbiental = new ColumnTO("Evaluacion Ambiental", 26);
        this.columnas.add( evaluacionAmbiental );
        
        // Plan de cumplimiento
        ColumnTO actoPlanCum = new ColumnTO("Acto Administrativo PC", 27);
        this.columnas.add( actoPlanCum );
                
        this.columnas.add( new ColumnTO("Fecha Expedicion PC", 28) );                
        this.columnas.add( new ColumnTO("Acto Administrativo Aprobacion PC", 29) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion PC", 30) );        
        this.columnas.add( new ColumnTO("Fecha Notificacion Aprobacion PC", 31) );
        this.columnas.add( new ColumnTO("Fecha Inicial Plan Cumplimiento", 32) );
        this.columnas.add( new ColumnTO("Fecha Final Plan Cumplimiento", 33) );
        
        // Permiso de Vertimiento
        this.columnas.add( new ColumnTO("Resolucion Permiso Vertimiento", 34) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Permiso Vertimiento", 35) );                
        this.columnas.add( new ColumnTO("Fecha Inicial Permiso Vertimiento", 36) );
        this.columnas.add( new ColumnTO("Fecha Final Permiso Vertimiento", 37) );
                
        this.columnas.add( new ColumnTO("Resolucion Aprobacion Planos", 38) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion Planos", 39) );                
        this.columnas.add( new ColumnTO("Resolucion Aprobacion Obras", 40) );                
        this.columnas.add( new ColumnTO("Fecha Expedicion Aprobacion Obras", 41) );
        this.columnas.add( new ColumnTO("Fecha Notificacion Aprobacion Obras", 42) );
        
        
        //punto vertimiento       
        ColumnTO tipoVert = new ColumnTO("Tipo Vertimiento", 43);
        tipoVert.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_VERTIMIENTO, 
                                                     "Tipo Vertimiento") );
        this.columnas.add( tipoVert );
        
        ColumnTO deptoVert = new ColumnTO("Departamento Vertimiento",44);
        deptoVert.addValidator( new Obligatorio() );        
        deptoVert.addValidator( new DepartamentoValidator() );
        this.columnas.add( deptoVert ); 
        
        ColumnTO munVert = new ColumnTO("Municipio Vertimiento",45);
        munVert.addValidator( new Obligatorio() );
        munVert.addValidator( new MunicipioValidator(deptoVert.getTitulo()) );
        this.columnas.add( munVert ); 
        
        this.columnas.add( new ColumnTO("Tipo centro Vertimiento", 46) );
        this.columnas.add( new ColumnTO("Nombre centro Vertimiento", 47) );
        
        ColumnTO areaVert = new ColumnTO("Area", 48);
        areaVert.addValidator( new Obligatorio() );        
        areaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( areaVert );
        
        ColumnTO zonaVert = new ColumnTO("Zona", 49);
        zonaVert.addValidator( new Obligatorio() );
        zonaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( zonaVert );

        ColumnTO subzonaVert = new ColumnTO("Subzona", 50);
        subzonaVert.addValidator( new Obligatorio() );
        subzonaVert.addValidator( new ZonificacionValidator() );
        this.columnas.add( subzonaVert );
        
        ColumnTO tipo = new ColumnTO("Tipo Fuente", 51);
        tipo.addValidator(new Obligatorio());
        tipo.addValidator(new ListasValidator(12L, "Tipo Fuente"));
        this.columnas.add( tipo );
        
        ColumnTO nombreFuenteCapta = new ColumnTO("Fuente", 52);
        nombreFuenteCapta.addValidator(new Obligatorio());    
        nombreFuenteCapta.addValidator(new FuenteTipoNoExisteValidator(tipo.
                                                                    getTitulo(), 
                                                                    12L)  );
        this.columnas.add( nombreFuenteCapta );
        
        ColumnTO nombreTramo = new ColumnTO("Tramo", 53);  
        nombreTramo.addValidator(new Obligatorio()); 
        nombreTramo.addValidator(new TramoCompositeValidator(tipo.getTitulo(), 
                                                             12L, 
                                                             nombreFuenteCapta.
                                                             getTitulo()));
        this.columnas.add( nombreTramo );
        
        ColumnTO tipoFlujo = new ColumnTO("Tipo Flujo", 54);
        tipoFlujo.addValidator( new ListasValidator(ConstantesParametros.
                                                     CATEGORIA_TIPO_FLUJO_VERTIMIENTO, 
                                                     "Tipo Flujo") );
        this.columnas.add( tipoFlujo );
        
        ColumnTO tiempoDescarga = new ColumnTO("Tiempo Descarga", 55);
        tiempoDescarga.addValidator(new Obligatorio());
        tiempoDescarga.addValidator( new DoubleValidator() );
        this.columnas.add( tiempoDescarga );

        ColumnTO frecuencia = new ColumnTO("Frecuencia", 56);
        frecuencia.addValidator(new Obligatorio());
        frecuencia.addValidator( new DoubleValidator() );
        this.columnas.add( frecuencia );
        
        ColumnTO caudalDescarga = new ColumnTO("Caudal Descarga", 57);
        caudalDescarga.addValidator(new Obligatorio());
        caudalDescarga.addValidator( new DoubleValidator() );
        this.columnas.add( caudalDescarga );

        ColumnTO preTratamiento = new ColumnTO("Pretratamiento", 58);
        preTratamiento.addValidator( new ListasOpcionalValidator(ConstantesParametros.CATEGORIA_PRETRATAMIENTO, "Pretratamiento") );
        this.columnas.add( preTratamiento );
        
        ColumnTO primario = new ColumnTO("Primario", 59);
        primario.addValidator( new ListasOpcionalValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_PRIMARIO, "Primario") );
        this.columnas.add( primario );
        
        ColumnTO secundario = new ColumnTO("Secundario", 60);
        secundario.addValidator( new ListasOpcionalValidator(ConstantesParametros. CATEGORIA_TRATAMIENTO_SECUNDARIO,  "Secundario") );
        this.columnas.add( secundario );
        
        ColumnTO terciario = new ColumnTO("Terciario", 61);
        terciario.addValidator( new ListasOpcionalValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_TERCIARIO, "Terciario") );
        this.columnas.add( terciario );
        
        ColumnTO otro = new ColumnTO("Otros", 62);
        otro.addValidator( new ListasOpcionalValidator(ConstantesParametros.CATEGORIA_TRATAMIENTO_OTRO, "Otros") );
        this.columnas.add( otro );        
                
        ColumnTO sistemaReferencia = new ColumnTO("Sistema Referencia", 63);   
        sistemaReferencia.addValidator( new Obligatorio() );
        sistemaReferencia.addValidator( new ListasValidator(ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA, 
                                                            "Sistema de Referencia") );
        this.columnas.add( sistemaReferencia );

        ColumnTO gradosLatitud = new ColumnTO("Grados Latitud", 64);
        gradosLatitud.addValidator( new Obligatorio() );
        gradosLatitud.addValidator( new LongValidator() );
        gradosLatitud.addValidator( new LongRangeValidator(-4L, 15L));
        this.columnas.add( gradosLatitud );
        
        ColumnTO minutosLatitud = new ColumnTO("Minutos Latitud", 65);
        minutosLatitud.addValidator( new Obligatorio() );
        minutosLatitud.addValidator( new LongValidator());
        minutosLatitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLatitud );
        
        ColumnTO segundosLatitud = new ColumnTO("Segundos Latitud", 66);
        segundosLatitud.addValidator( new Obligatorio() );
        segundosLatitud.addValidator( new DoubleValidator() );
        segundosLatitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLatitud );
        
        ColumnTO gradosLongitud = new ColumnTO("Grados Longitud", 67);
        gradosLongitud.addValidator( new Obligatorio() );
        gradosLongitud.addValidator( new LongRangeValidator() );
        gradosLongitud.addValidator( new LongRangeValidator(-82L, -66L));
        this.columnas.add( gradosLongitud );
        
        ColumnTO minutosLongitud = new ColumnTO("Minutos Longitud", 68);
        minutosLongitud.addValidator( new Obligatorio() );
        minutosLongitud.addValidator( new LongValidator());
        minutosLongitud.addValidator( new LongRangeValidator(0L,59L) );
        this.columnas.add( minutosLongitud );
        
        ColumnTO segundosLongitud = new ColumnTO("Segundos Longitud", 69);
        segundosLongitud.addValidator( new Obligatorio() );
        segundosLongitud.addValidator( new DoubleValidator() );
        segundosLongitud.addValidator( new DoubleRangeValidator(0D, 59.99999D) );
        this.columnas.add( segundosLongitud );

        ColumnTO altitud = new ColumnTO("Altitud", 70);
        altitud.addValidator( new Obligatorio() );
        altitud.addValidator( new DoubleValidator() );
        altitud.addValidator( new DoubleRangeValidator(0D,5500D) );
        this.columnas.add( altitud );
        
        ColumnTO descripcion = new ColumnTO("Descripcion", 71);
        this.columnas.add( descripcion );
        
    }
}
