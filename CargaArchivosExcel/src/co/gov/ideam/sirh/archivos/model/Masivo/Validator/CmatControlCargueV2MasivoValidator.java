package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import java.util.ArrayList;

public class CmatControlCargueV2MasivoValidator extends AbstractValidator {
    public CmatControlCargueV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO id = new ColumnTO("Id Cargue", 0);
        id.addValidator(new Obligatorio());
        this.columnas.add(id);

        ColumnTO fechaCargue = new ColumnTO("Fecha del Cargue", 1);
        fechaCargue.addValidator(new Obligatorio());
        this.columnas.add(fechaCargue);

        ColumnTO origenCargue = new ColumnTO("Origen del cargue", 2);
        origenCargue.addValidator(new Obligatorio());
        this.columnas.add(origenCargue);

        ColumnTO idAutoridad = new ColumnTO("id Autoridad", 3);
        idAutoridad.addValidator(new Obligatorio());
        this.columnas.add(idAutoridad);

        ColumnTO idUsuarioCargue = new ColumnTO("id usuario cargue", 4);
        idUsuarioCargue.addValidator(new Obligatorio());
        this.columnas.add(idUsuarioCargue);

        ColumnTO idTipoPlantilla = new ColumnTO("id tipo plantilla", 5);
        idTipoPlantilla.addValidator(new Obligatorio());
        this.columnas.add(idTipoPlantilla);

        ColumnTO estadoCargue = new ColumnTO("estado cargue", 6);
        estadoCargue.addValidator(new Obligatorio());
        this.columnas.add(estadoCargue);

        ColumnTO fechaEstado = new ColumnTO("fecha estado", 7);
        fechaEstado.addValidator(new Obligatorio());
        this.columnas.add(fechaEstado);

        ColumnTO idUsuarioEstado = new ColumnTO("id usuario estado", 8);
        idUsuarioEstado.addValidator(new Obligatorio());
        this.columnas.add(idUsuarioEstado);

        ColumnTO codigoRechazo = new ColumnTO("codigo rechazo", 9);
        codigoRechazo.addValidator(new Obligatorio());
        this.columnas.add(codigoRechazo);

        ColumnTO cantidadRegistros = new ColumnTO("cantidad registros", 10);
        cantidadRegistros.addValidator(new Obligatorio());
        this.columnas.add(cantidadRegistros);
    }
}
