package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasManantialV2MasivoValidator extends AbstractValidator {
    public CmatFuniasManantialV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 1);
        this.columnas.add(IDregistro);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 2);
        this.columnas.add(codigorechazo);
        ColumnTO idptoaguasubt = new ColumnTO("Id Pto Agua Subt", 3);
        this.columnas.add(idptoaguasubt);
        ColumnTO tipo = new ColumnTO("Tipo", 4);
        this.columnas.add(tipo);
        ColumnTO otrotipo = new ColumnTO("Otro Tipo", 5);
        this.columnas.add(otrotipo);
        ColumnTO permanencia = new ColumnTO("Permanencia", 6);
        this.columnas.add(permanencia);
        ColumnTO mediosurgencia = new ColumnTO("Medio Surgencia", 7);
        this.columnas.add(mediosurgencia);
        ColumnTO observaciones = new ColumnTO("Observaciones", 8);
        this.columnas.add(observaciones);

    }
}
