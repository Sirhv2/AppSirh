package co.gov.ideam.sirh.archivos.model;


import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;


import co.gov.ideam.sirh.archivos.model.validator.PuntoNoExisteValidator;
import co.gov.ideam.sirh.util.ConstantesCalidad;

import java.util.ArrayList;

public class MuestrasMedicionesValidator extends AbstractValidator{
 
    public MuestrasMedicionesValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO id = new ColumnTO("ID PUNTO", 0);
        this.columnas.add( id );
             
        ColumnTO nombre = new ColumnTO("Nombre Punto", 1);
        nombre.addValidator(new Obligatorio());     
        nombre.addValidator( new PuntoNoExisteValidator() );
        this.columnas.add( nombre );
        
        ColumnTO observacion = new ColumnTO("Observacion", 2);
        this.columnas.add( observacion );
        
        ColumnTO laboratorio = new ColumnTO("Laboratorio",3);
        laboratorio.addValidator(new Obligatorio());
        laboratorio.addValidator(new ListasValidator(ConstantesCalidad.LABORATORIO, "Laboratorios"));
        this.columnas.add( laboratorio );

        ColumnTO otroLab = new ColumnTO("Cual?",4);        
        this.columnas.add( otroLab );
        
        ColumnTO tipoMuestra = new ColumnTO("Tipo Muestra",5);
        tipoMuestra.addValidator( new Obligatorio() );        
        tipoMuestra.addValidator(new ListasValidator(ConstantesCalidad.TIPO_MUESTRA, "Tipo de muestras"));      
        this.columnas.add( tipoMuestra );
               
        ColumnTO fecha = new ColumnTO("Fecha", 6);
        fecha.addValidator( new Obligatorio() );
        this.columnas.add( fecha );
        

        ColumnTO horam = new ColumnTO("Hora Muestreo", 7);
        horam.addValidator( new Obligatorio() );
        this.columnas.add( horam );
        
        ColumnTO hora = new ColumnTO("Hora", 8);
        hora.addValidator( new Obligatorio() );
        this.columnas.add( hora );
        
        ColumnTO min = new ColumnTO("Min", 9);
        min.addValidator( new Obligatorio() );
        this.columnas.add( min );
        
        ColumnTO horario = new ColumnTO("Horario", 10);
        horario.addValidator( new Obligatorio() );
        this.columnas.add( horario );
        
        ColumnTO caudal = new ColumnTO("Caudal", 11);
        caudal.addValidator( new Obligatorio() );
        this.columnas.add( caudal );

        ColumnTO nroVerticales = new ColumnTO("Nro de verticales", 12);
        this.columnas.add( nroVerticales );
        
        ColumnTO intervaloTiempo = new ColumnTO("Intervalo de Tiempo", 13);
        this.columnas.add( intervaloTiempo );

        ColumnTO duracion = new ColumnTO("Duracion", 14);   
        this.columnas.add( duracion );
        
        ColumnTO nroSubmuestras = new ColumnTO("Nro de Submuestras", 15);
        this.columnas.add( nroSubmuestras);
        
        ColumnTO parametro = new ColumnTO("Parametros", 16);
        parametro.addValidator(new ListasValidator(ConstantesCalidad.PARAMETROS_CALIDAD, "Parametros de calidad"));   
        this.columnas.add( parametro );
        
        ColumnTO signo = new ColumnTO("Signo", 17);
        signo.addValidator(new ListasValidator(ConstantesCalidad.SIGNOS,  "Signos - calidad"));   
        this.columnas.add( signo );
        
        ColumnTO unidad = new ColumnTO("Unidad", 18);
        unidad.addValidator(new ListasValidator(ConstantesCalidad.UNIDADES_CALIDAD,  "Unidades de medida - calidad"));   
        this.columnas.add( unidad );
        
        ColumnTO valor = new ColumnTO("Valor", 19);
        valor.addValidator( new Obligatorio() );
        this.columnas.add( valor );
        
        ColumnTO valor2 = new ColumnTO("Valor 2", 20);
       
        this.columnas.add( valor2 );
        
        ColumnTO valorCARACTER = new ColumnTO("Valor Caracter", 21);
        this.columnas.add( valorCARACTER );
        
        ColumnTO metodo = new ColumnTO("Metodo de determinación", 22);
       
        this.columnas.add( metodo );

        ColumnTO limite = new ColumnTO("Limite de detección", 23);
        this.columnas.add( limite );
        
        ColumnTO acreditado = new ColumnTO("Acreditado", 24);
        this.columnas.add( acreditado );
        
        
        
        
    }
}

