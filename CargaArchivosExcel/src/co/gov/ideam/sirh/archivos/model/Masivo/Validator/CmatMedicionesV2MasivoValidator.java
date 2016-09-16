package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatMedicionesV2MasivoValidator extends AbstractValidator {
    public CmatMedicionesV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO identificadormuestra =
            new ColumnTO("Identificador Muestra", 3);
        this.columnas.add(identificadormuestra);
        ColumnTO nombrepunto = new ColumnTO("Nombre Punto", 4);
        this.columnas.add(nombrepunto);
        ColumnTO descripcion = new ColumnTO("Descripcion", 5);
        this.columnas.add(descripcion);
        ColumnTO nombrelaboratorio = new ColumnTO("Nombre Laboratorio", 6);
        this.columnas.add(nombrelaboratorio);
        ColumnTO otrolaboratorio = new ColumnTO("Otro Laboratorio", 7);
        this.columnas.add(otrolaboratorio);
        ColumnTO tipomuestra = new ColumnTO("Tipo Muestra", 8);
        this.columnas.add(tipomuestra);
        ColumnTO fecha = new ColumnTO("Fecha", 9);
        this.columnas.add(fecha);
        ColumnTO hora = new ColumnTO("Hora", 10);
        this.columnas.add(hora);
        ColumnTO min = new ColumnTO("Min", 11);
        this.columnas.add(min);
        ColumnTO horario = new ColumnTO("Horario", 12);
        this.columnas.add(horario);
        ColumnTO caudal = new ColumnTO("Caudal", 13);
        this.columnas.add(caudal);
        ColumnTO numverticales = new ColumnTO("Num Verticales", 14);
        this.columnas.add(numverticales);
        ColumnTO intervalotiempo = new ColumnTO("Intervalo Tiempo", 15);
        this.columnas.add(intervalotiempo);
        ColumnTO duracionmuestreo = new ColumnTO("Duracion Muestreo", 16);
        this.columnas.add(duracionmuestreo);
        ColumnTO numsubmuestras = new ColumnTO("Num Submuestras", 17);
        this.columnas.add(numsubmuestras);
        ColumnTO parametro = new ColumnTO("Parametro", 18);
        this.columnas.add(parametro);
        ColumnTO signo = new ColumnTO("Signo", 19);
        this.columnas.add(signo);
        ColumnTO unidadmedida = new ColumnTO("Unidad Medida", 20);
        this.columnas.add(unidadmedida);
        ColumnTO valor = new ColumnTO("Valor", 21);
        this.columnas.add(valor);
        ColumnTO valor2 = new ColumnTO("Valor 2", 22);
        this.columnas.add(valor2);
        ColumnTO valorunico = new ColumnTO("Valor Unico", 23);
        this.columnas.add(valorunico);
        ColumnTO metododeterminacion =
            new ColumnTO("Metodo Determinacion", 24);
        this.columnas.add(metododeterminacion);
        ColumnTO limitedeteccion = new ColumnTO("Limite Deteccion", 25);
        this.columnas.add(limitedeteccion);
        ColumnTO parametroacreditado =
            new ColumnTO("Parametro Acreditado", 26);
        this.columnas.add(parametroacreditado);

    }
}
