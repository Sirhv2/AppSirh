package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasGeofisicaV2MasivoValidator extends AbstractValidator {
    public CmatFuniasGeofisicaV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idptoaguasub = new ColumnTO("Id Pto Agua Sub", 3);
        this.columnas.add(idptoaguasub);
        ColumnTO fechamedida = new ColumnTO("Fecha Medida", 4);
        this.columnas.add(fechamedida);
        ColumnTO equipoutilizado = new ColumnTO("Equipo Utilizado", 5);
        this.columnas.add(equipoutilizado);
        ColumnTO tiporegistro = new ColumnTO("Tipo Registro", 6);
        this.columnas.add(tiporegistro);
        ColumnTO compañiaejecutora = new ColumnTO("Compañia Ejecutora", 7);
        this.columnas.add(compañiaejecutora);
        ColumnTO resistividadlodo = new ColumnTO("Resistividad Lodo", 8);
        this.columnas.add(resistividadlodo);
        ColumnTO temperaturalodo = new ColumnTO("Temperatura Lodo", 9);
        this.columnas.add(temperaturalodo);
        ColumnTO profundidadregistro =
            new ColumnTO("Profundidad Registro", 10);
        this.columnas.add(profundidadregistro);
        ColumnTO calidadregistro = new ColumnTO("Calidad Registro", 11);
        this.columnas.add(calidadregistro);
    }
}
