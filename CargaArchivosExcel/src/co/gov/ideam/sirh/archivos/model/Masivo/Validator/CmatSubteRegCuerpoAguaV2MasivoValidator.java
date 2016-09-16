package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatSubteRegCuerpoAguaV2MasivoValidator extends AbstractValidator {
    public CmatSubteRegCuerpoAguaV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idfuente = new ColumnTO("Id Fuente", 3);
        this.columnas.add(idfuente);
        ColumnTO tipo = new ColumnTO("Tipo", 4);
        this.columnas.add(tipo);
        ColumnTO provinciahidro = new ColumnTO("Provincia Hidro", 5);
        this.columnas.add(provinciahidro);
        ColumnTO sistemaacuifero = new ColumnTO("Sistema Acuifero", 6);
        this.columnas.add(sistemaacuifero);
        ColumnTO nombreunidadhidro = new ColumnTO("Nombre Unidad Hidro", 7);
        this.columnas.add(nombreunidadhidro);
        ColumnTO descripcion = new ColumnTO("Descripcion", 8);
        this.columnas.add(descripcion);
        ColumnTO conductividad = new ColumnTO("Conductividad", 9);
        this.columnas.add(conductividad);
        ColumnTO transmisividad = new ColumnTO("Transmisividad", 10);
        this.columnas.add(transmisividad);
        ColumnTO caracteristicas = new ColumnTO("Caracteristicas", 11);
        this.columnas.add(caracteristicas);
        ColumnTO espesor = new ColumnTO("Espesor", 12);
        this.columnas.add(espesor);

    }
}
