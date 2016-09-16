package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasTopografiaGeoV2MasivoValidator extends AbstractValidator {
    public CmatFuniasTopografiaGeoV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idptoaguassub = new ColumnTO("Id Pto Aguas Sub", 3);
        this.columnas.add(idptoaguassub);
        ColumnTO localizaciontopografica =
            new ColumnTO("Localizacion Topografica", 4);
        this.columnas.add(localizaciontopografica);
        ColumnTO unidadgeologica = new ColumnTO("Unidad Geologica", 5);
        this.columnas.add(unidadgeologica);
        ColumnTO geoforma = new ColumnTO("Geoforma", 6);
        this.columnas.add(geoforma);
        ColumnTO descripcionlitologia =
            new ColumnTO("Descripcion Litologia", 7);
        this.columnas.add(descripcionlitologia);
    }
}
