package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;

import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasExplotacionV2MasivoValidator extends AbstractValidator {
    public CmatFuniasExplotacionV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idpuntoaguassub = new ColumnTO("Id Punto Aguas Sub", 3);
        this.columnas.add(idpuntoaguassub);
        ColumnTO metodoexplotacion = new ColumnTO("Metodo Explotacion", 4);
        this.columnas.add(metodoexplotacion);
        ColumnTO tipoenergia = new ColumnTO("Tipo Energia", 5);
        this.columnas.add(tipoenergia);
        ColumnTO clasebomba = new ColumnTO("Clase Bomba", 6);
        this.columnas.add(clasebomba);
        ColumnTO modelobomba = new ColumnTO("Modelo Bomba", 7);
        this.columnas.add(modelobomba);
        ColumnTO potenciabomba = new ColumnTO("Potencia Bomba", 8);
        this.columnas.add(potenciabomba);
        ColumnTO profundidadsuccion = new ColumnTO("Profundidad Succion", 9);
        this.columnas.add(profundidadsuccion);
        ColumnTO diametrotuberiadescarga =
            new ColumnTO("Diametro Tuberia Descarga", 10);
        this.columnas.add(diametrotuberiadescarga);
        ColumnTO longitudtubdescarga =
            new ColumnTO("Longitud Tub Descarga", 11);
        this.columnas.add(longitudtubdescarga);
        ColumnTO materialtubdescarga =
            new ColumnTO("Material Tub Descarga", 12);
        this.columnas.add(materialtubdescarga);

    }
}
