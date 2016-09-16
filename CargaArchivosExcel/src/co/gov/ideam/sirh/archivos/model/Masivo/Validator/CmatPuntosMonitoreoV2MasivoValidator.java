package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPuntosMonitoreoV2MasivoValidator extends AbstractValidator {
    public CmatPuntosMonitoreoV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idfuente = new ColumnTO("Id Fuente", 3);
        this.columnas.add(idfuente);
        ColumnTO nombrepunto = new ColumnTO("Nombre Punto", 4);
        this.columnas.add(nombrepunto);
        ColumnTO tipopunto = new ColumnTO("Tipo Punto", 5);
        this.columnas.add(tipopunto);
        ColumnTO ubicacion = new ColumnTO("Ubicacion", 6);
        this.columnas.add(ubicacion);
        ColumnTO departamento = new ColumnTO("Departamento", 7);
        this.columnas.add(departamento);
        ColumnTO municipio = new ColumnTO("Municipio", 8);
        this.columnas.add(municipio);
        ColumnTO area = new ColumnTO("Area", 9);
        this.columnas.add(area);
        ColumnTO zona = new ColumnTO("Zona", 10);
        this.columnas.add(zona);
        ColumnTO subzona = new ColumnTO("Subzona", 11);
        this.columnas.add(subzona);
        ColumnTO tipo = new ColumnTO("Tipo", 12);
        this.columnas.add(tipo);
        ColumnTO fuente = new ColumnTO("Fuente", 13);
        this.columnas.add(fuente);
        ColumnTO tramo = new ColumnTO("Tramo", 14);
        this.columnas.add(tramo);
        ColumnTO sistemareferencia = new ColumnTO("Sistema Referencia", 15);
        this.columnas.add(sistemareferencia);
        ColumnTO gradoslatitud = new ColumnTO("Grados Latitud", 16);
        this.columnas.add(gradoslatitud);
        ColumnTO minutoslatitud = new ColumnTO("Minutos Latitud", 17);
        this.columnas.add(minutoslatitud);
        ColumnTO segundoslatitud = new ColumnTO("Segundos Latitud", 18);
        this.columnas.add(segundoslatitud);
        ColumnTO gradoslongitud = new ColumnTO("Grados Longitud", 19);
        this.columnas.add(gradoslongitud);
        ColumnTO minutoslongitud = new ColumnTO("Minutos Longitud", 20);
        this.columnas.add(minutoslongitud);
        ColumnTO segundoslongitud = new ColumnTO("Segundos Longitud", 21);
        this.columnas.add(segundoslongitud);
        ColumnTO altitud = new ColumnTO("Altitud", 22);
        this.columnas.add(altitud);
        ColumnTO descripcionacceso = new ColumnTO("Descripcion Acceso", 23);
        this.columnas.add(descripcionacceso);

    }
}
